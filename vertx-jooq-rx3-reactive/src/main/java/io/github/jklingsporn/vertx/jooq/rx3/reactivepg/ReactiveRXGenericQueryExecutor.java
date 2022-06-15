package io.github.jklingsporn.vertx.jooq.rx3.reactivepg;

import io.github.jklingsporn.vertx.jooq.rx3.RXQueryExecutor;
import io.github.jklingsporn.vertx.jooq.shared.internal.QueryResult;
import io.github.jklingsporn.vertx.jooq.shared.reactive.AbstractReactiveQueryExecutor;
import io.github.jklingsporn.vertx.jooq.shared.reactive.ReactiveQueryExecutor;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.rxjava3.sqlclient.Transaction;
import io.vertx.rxjava3.sqlclient.*;
import io.vertx.sqlclient.Row;
import org.jooq.Query;
import org.jooq.Record;
import org.jooq.*;
import org.jooq.exception.TooManyRowsException;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by jensklingsporn on 01.03.18.
 */
public class ReactiveRXGenericQueryExecutor extends AbstractReactiveQueryExecutor implements ReactiveQueryExecutor<Single<List<Row>>,Single<Optional<Row>>,Single<Integer>>, RXQueryExecutor {

    protected final SqlClient delegate;
    protected final Transaction transaction;


    public ReactiveRXGenericQueryExecutor(Configuration configuration, SqlClient delegate) {
        this(configuration, delegate, null);
    }

    ReactiveRXGenericQueryExecutor(Configuration configuration, SqlClient delegate, Transaction transaction) {
        super(configuration);
        this.delegate = delegate;
        this.transaction = transaction;
    }

    @Override
    public <Q extends Record> Single<List<Row>> findManyRow(Function<DSLContext, ? extends ResultQuery<Q>> queryFunction) {
        return executeAny(queryFunction).map(res ->
                StreamSupport
                .stream(rxGetDelegate(res).spliterator(), false)
                        .collect(Collectors.toList()));
    }

    @SuppressWarnings("unchecked")
    /**
     * for some reason getDelegate returns untyped version
     */
    io.vertx.sqlclient.RowSet<io.vertx.sqlclient.Row> rxGetDelegate(RowSet<io.vertx.rxjava3.sqlclient.Row> res) {
        return res.getDelegate();
    }

    @Override
    public <Q extends Record> Single<Optional<Row>> findOneRow(Function<DSLContext, ? extends ResultQuery<Q>> queryFunction) {
        return executeAny(queryFunction).map(res-> {
            switch (res.size()) {
                case 0: return Optional.empty();
                case 1: return Optional.ofNullable(rxGetDelegate(res).iterator().next());
                default: throw new TooManyRowsException(String.format("Found more than one row: %d", res.size()));
            }
        });
    }

    @Override
    public Single<Integer> execute(Function<DSLContext, ? extends Query> queryFunction) {
        return executeAny(queryFunction).map(SqlResult::rowCount);
    }

    protected Tuple rxGetBindValues(Query query) {
        Tuple tuple = Tuple.tuple();
        query.getParams()
                .values()
                .stream()
                .filter(param -> !param.isInline())
                .map(this::convertToDatabaseType)
                .forEach(tuple::addValue);
        return tuple;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <R extends Record> Single<QueryResult> query(Function<DSLContext, ? extends ResultQuery<R>> queryFunction) {
        try{
            Query query = createQuery(queryFunction);
            log(query);
            Single<RowSet<io.vertx.rxjava3.sqlclient.Row>> rowSingle = delegate.preparedQuery(toPreparedQuery(query)).rxExecute(rxGetBindValues(query));
            return rowSingle.map(RxReactiveQueryResult::new);
        }catch (Throwable e){
            return Single.error(e);
        }
    }

    /**
     * @return an instance of a <code>ReactiveRXGenericQueryExecutor</code> that performs all CRUD
     * functions in the scope of a transaction. The transaction has to be committed/rolled back by calling <code>commit</code>
     * or <code>rollback</code> on the QueryExecutor returned.
     */
    public Single<? extends ReactiveRXGenericQueryExecutor> beginTransaction(){
        if(transaction != null){
            return Single.error(new IllegalStateException("Already in transaction"));
        }
        return delegateAsPool()
                .flatMap(Pool::rxGetConnection)
                .flatMap(conn->conn.rxBegin().map(newInstance(conn)));
    }

    protected io.reactivex.rxjava3.functions.Function<Transaction, ? extends ReactiveRXGenericQueryExecutor> newInstance(SqlConnection conn) {
        return transaction -> new ReactiveRXGenericQueryExecutor(configuration(),conn,transaction);
    }

    /**
     * Commits a transaction.
     * @return a <code>Completable</code> that completes when the transaction has been committed or fails if
     * the user hasn't called <code>beginTransaction</code> before.
     * @since 6.4.1 This method no longer throws an IllegalStateException.
     */
    public Completable commit(){
        if(transaction==null){
            return Completable.error(new IllegalStateException("Not in transaction"));
        }
        return transaction.rxCommit()
                .andThen(Completable.defer(delegate::close))
                .onErrorResumeNext(x-> delegate.close().andThen(Completable.error(x)));
    }

    /**
     * Rolls a transaction back.
     * @return a <code>Completable</code> that completes when the transaction has been rolled back
     * or fails if the user hasn't called <code>beginTransaction</code> before.
     * @since 6.4.1 This method no longer throws an IllegalStateException.
     */
    public Completable rollback(){
        if(transaction==null){
            return Completable.error(new IllegalStateException("Not in transaction"));
        }
        return transaction.rxRollback()
                .andThen(Completable.defer(delegate::close))
                .onErrorResumeNext(x-> delegate.close().andThen(Completable.error(x)));
    }

    /**
     * Convenience method to perform multiple calls on a transactional QueryExecutor, committing the transaction and
     * returning a result.
     * @param transaction your code using a transactional QueryExecutor.
     *                    <pre>
     *                    {@code
     *                    ReactiveRXGenericQueryExecutor nonTransactionalQueryExecutor...;
     *                    Maybe<QueryResult> resultOfTransaction = nonTransactionalQueryExecutor.transaction(transactionalQueryExecutor ->
     *                      {
     *                          //make all calls on the provided QueryExecutor that runs all code in a transaction
     *                          return transactionalQueryExecutor.execute(dslContext -> dslContext.insertInto(Tables.XYZ)...)
     *                              .compose(i -> transactionalQueryExecutor.query(dslContext -> dslContext.selectFrom(Tables.XYZ).where(Tables.XYZ.SOME_VALUE.eq("FOO")));
     *                      }
     *                    );
     *                    }
     *                    </pre>
     * @param <U> the return type.
     * @return the result of the transaction.
     */
    public <U> Maybe<U> transaction(io.reactivex.rxjava3.functions.Function<ReactiveRXGenericQueryExecutor, Maybe<U>> transaction){
        return delegateAsPool()
                .flatMapMaybe(pool -> pool.rxWithTransaction(sqlConnection -> {
                    try {
                        return transaction.apply(new ReactiveRXGenericQueryExecutor(configuration(), sqlConnection));
                    } catch (Throwable e) {
                        return Maybe.error(e);
                    }
                }));
    }

    @Override
    public void release() {
        if(delegate!=null){
            delegate.close();
        }
    }

    /**
     * Executes the given queryFunction and returns a <code>RowSet</code>
     * @param queryFunction the query to execute
     * @return the results, never null
     */
    public Single<RowSet<io.vertx.rxjava3.sqlclient.Row>> executeAny(Function<DSLContext, ? extends Query> queryFunction) {
        try{
            Query query = createQuery(queryFunction);
            log(query);
            return delegate.preparedQuery(toPreparedQuery(query)).rxExecute(rxGetBindValues(query));
        }catch (Throwable e){
            return Single.error(e);
        }
    }

    /**
     * A convenient function to process a large result set using a {@link io.reactivex.rxjava3.core.Flowable} based on a
     * {@link io.vertx.rxjava3.sqlclient.RowStream}. This function borrows a connection from the bool and
     * starts a transaction as long as the <code>Flowable</code> processes items. After completion, the transaction
     * is committed and the connection is closed and put back into the pool.
     * @param queryFunction The function that fetches the result set.
     * @param fetchSize the amount to fetch
     * @return a <code>Flowable</code> to process the large result.
     * @see #queryFlowableRow(Function, int, Handler, Handler)
     */
    public Flowable<io.vertx.rxjava3.sqlclient.Row> queryFlowableRow(Function<DSLContext, ? extends Query> queryFunction, int fetchSize){
        return queryFlowableRow(queryFunction,fetchSize, r->{}, r->{});
    }

    /**
     * A convenient function to process a large result set using a {@link io.reactivex.rxjava3.core.Flowable} based on a
     * {@link io.vertx.rxjava3.sqlclient.RowStream}. This function borrows a connection from the bool and
     * starts a transaction as long as the <code>Flowable</code> processes items. After completion, the transaction
     * is committed and the connection is closed and put back into the pool.
     * @param queryFunction The function that fetches the result set.
     * @param fetchSize the amount to fetch
     * @param commitHandler the handler that is notified when the transaction has been committed, either successfully or with a failure
     * @param closeHandler the handler that is notified when the connection was closed, either successfully or with a failure
     * @return a <code>Flowable</code> to process the large result.
     * @see #queryFlowableRow(Function, int, Handler, Handler)
     */
    public Flowable<io.vertx.rxjava3.sqlclient.Row> queryFlowableRow(Function<DSLContext, ? extends Query> queryFunction,
                                                                       int fetchSize,
                                                                       Handler<AsyncResult<Void>> commitHandler,
                                                                       Handler<AsyncResult<Void>> closeHandler){
        Query query = createQuery(queryFunction);
        return delegateAsPool()
                .flatMap(Pool::rxGetConnection)
                .flatMapPublisher(conn -> conn
                        .rxBegin()
                        .flatMapPublisher(tx ->
                                conn
                                        .rxPrepare(toPreparedQuery(query))
                                        .flatMapPublisher(preparedQuery -> preparedQuery.createStream(fetchSize).toFlowable())
                                        .doAfterTerminate(() -> tx.getDelegate().commit(commitHandler))
                                        .doAfterTerminate(() -> conn.getDelegate().close(closeHandler))
                        )
                );
    }

    protected Single<Pool> delegateAsPool(){
        if(!(delegate instanceof Pool)){
            return Single.error(new IllegalStateException("delegate must be an instance of Pool. Are you calling from inside a transaction?"));
        }
        Pool pool = (Pool) this.delegate;
        return Single.just(pool);
    }
}
