/*
 * This file is generated by jOOQ.
 */
package generated.rx.jdbc.regular.vertx.tables.daos;


import generated.rx.jdbc.regular.vertx.tables.Something;
import generated.rx.jdbc.regular.vertx.tables.records.SomethingRecord;

import io.github.jklingsporn.vertx.jooq.shared.internal.AbstractVertxDAO;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.time.LocalDateTime;
import java.util.Collection;

import org.jooq.Configuration;


import java.util.List;
import io.reactivex.Single;
import java.util.Optional;
import io.github.jklingsporn.vertx.jooq.rx.jdbc.JDBCRXQueryExecutor;
/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SomethingDao extends AbstractVertxDAO<SomethingRecord, generated.rx.jdbc.regular.vertx.tables.pojos.Something, Integer, Single<List<generated.rx.jdbc.regular.vertx.tables.pojos.Something>>, Single<Optional<generated.rx.jdbc.regular.vertx.tables.pojos.Something>>, Single<Integer>, Single<Integer>> implements io.github.jklingsporn.vertx.jooq.rx.VertxDAO<SomethingRecord,generated.rx.jdbc.regular.vertx.tables.pojos.Something,Integer> {

        /**
     * @param configuration The Configuration used for rendering and query execution.
     * @param vertx the vertx instance
     */
        public SomethingDao(Configuration configuration, io.vertx.reactivex.core.Vertx vertx) {
                super(Something.SOMETHING, generated.rx.jdbc.regular.vertx.tables.pojos.Something.class, new JDBCRXQueryExecutor<SomethingRecord,generated.rx.jdbc.regular.vertx.tables.pojos.Something,Integer>(configuration,generated.rx.jdbc.regular.vertx.tables.pojos.Something.class,vertx));
        }

        @Override
        protected Integer getId(generated.rx.jdbc.regular.vertx.tables.pojos.Something object) {
                return object.getSomeid();
        }

        /**
     * Find records that have <code>SOMESTRING IN (values)</code> asynchronously
     */
        public Single<List<generated.rx.jdbc.regular.vertx.tables.pojos.Something>> findManyBySomestring(Collection<String> values) {
                return findManyByCondition(Something.SOMETHING.SOMESTRING.in(values));
        }

        /**
     * Find records that have <code>SOMESTRING IN (values)</code> asynchronously limited by the given limit
     */
        public Single<List<generated.rx.jdbc.regular.vertx.tables.pojos.Something>> findManyBySomestring(Collection<String> values, int limit) {
                return findManyByCondition(Something.SOMETHING.SOMESTRING.in(values),limit);
        }

        /**
     * Find records that have <code>SOMEHUGENUMBER IN (values)</code> asynchronously
     */
        public Single<List<generated.rx.jdbc.regular.vertx.tables.pojos.Something>> findManyBySomehugenumber(Collection<Long> values) {
                return findManyByCondition(Something.SOMETHING.SOMEHUGENUMBER.in(values));
        }

        /**
     * Find records that have <code>SOMEHUGENUMBER IN (values)</code> asynchronously limited by the given limit
     */
        public Single<List<generated.rx.jdbc.regular.vertx.tables.pojos.Something>> findManyBySomehugenumber(Collection<Long> values, int limit) {
                return findManyByCondition(Something.SOMETHING.SOMEHUGENUMBER.in(values),limit);
        }

        /**
     * Find records that have <code>SOMESMALLNUMBER IN (values)</code> asynchronously
     */
        public Single<List<generated.rx.jdbc.regular.vertx.tables.pojos.Something>> findManyBySomesmallnumber(Collection<Short> values) {
                return findManyByCondition(Something.SOMETHING.SOMESMALLNUMBER.in(values));
        }

        /**
     * Find records that have <code>SOMESMALLNUMBER IN (values)</code> asynchronously limited by the given limit
     */
        public Single<List<generated.rx.jdbc.regular.vertx.tables.pojos.Something>> findManyBySomesmallnumber(Collection<Short> values, int limit) {
                return findManyByCondition(Something.SOMETHING.SOMESMALLNUMBER.in(values),limit);
        }

        /**
     * Find records that have <code>SOMEREGULARNUMBER IN (values)</code> asynchronously
     */
        public Single<List<generated.rx.jdbc.regular.vertx.tables.pojos.Something>> findManyBySomeregularnumber(Collection<Integer> values) {
                return findManyByCondition(Something.SOMETHING.SOMEREGULARNUMBER.in(values));
        }

        /**
     * Find records that have <code>SOMEREGULARNUMBER IN (values)</code> asynchronously limited by the given limit
     */
        public Single<List<generated.rx.jdbc.regular.vertx.tables.pojos.Something>> findManyBySomeregularnumber(Collection<Integer> values, int limit) {
                return findManyByCondition(Something.SOMETHING.SOMEREGULARNUMBER.in(values),limit);
        }

        /**
     * Find records that have <code>SOMEBOOLEAN IN (values)</code> asynchronously
     */
        public Single<List<generated.rx.jdbc.regular.vertx.tables.pojos.Something>> findManyBySomeboolean(Collection<Boolean> values) {
                return findManyByCondition(Something.SOMETHING.SOMEBOOLEAN.in(values));
        }

        /**
     * Find records that have <code>SOMEBOOLEAN IN (values)</code> asynchronously limited by the given limit
     */
        public Single<List<generated.rx.jdbc.regular.vertx.tables.pojos.Something>> findManyBySomeboolean(Collection<Boolean> values, int limit) {
                return findManyByCondition(Something.SOMETHING.SOMEBOOLEAN.in(values),limit);
        }

        /**
     * Find records that have <code>SOMEDOUBLE IN (values)</code> asynchronously
     */
        public Single<List<generated.rx.jdbc.regular.vertx.tables.pojos.Something>> findManyBySomedouble(Collection<Double> values) {
                return findManyByCondition(Something.SOMETHING.SOMEDOUBLE.in(values));
        }

        /**
     * Find records that have <code>SOMEDOUBLE IN (values)</code> asynchronously limited by the given limit
     */
        public Single<List<generated.rx.jdbc.regular.vertx.tables.pojos.Something>> findManyBySomedouble(Collection<Double> values, int limit) {
                return findManyByCondition(Something.SOMETHING.SOMEDOUBLE.in(values),limit);
        }

        /**
     * Find records that have <code>SOMEJSONOBJECT IN (values)</code> asynchronously
     */
        public Single<List<generated.rx.jdbc.regular.vertx.tables.pojos.Something>> findManyBySomejsonobject(Collection<JsonObject> values) {
                return findManyByCondition(Something.SOMETHING.SOMEJSONOBJECT.in(values));
        }

        /**
     * Find records that have <code>SOMEJSONOBJECT IN (values)</code> asynchronously limited by the given limit
     */
        public Single<List<generated.rx.jdbc.regular.vertx.tables.pojos.Something>> findManyBySomejsonobject(Collection<JsonObject> values, int limit) {
                return findManyByCondition(Something.SOMETHING.SOMEJSONOBJECT.in(values),limit);
        }

        /**
     * Find records that have <code>SOMEJSONARRAY IN (values)</code> asynchronously
     */
        public Single<List<generated.rx.jdbc.regular.vertx.tables.pojos.Something>> findManyBySomejsonarray(Collection<JsonArray> values) {
                return findManyByCondition(Something.SOMETHING.SOMEJSONARRAY.in(values));
        }

        /**
     * Find records that have <code>SOMEJSONARRAY IN (values)</code> asynchronously limited by the given limit
     */
        public Single<List<generated.rx.jdbc.regular.vertx.tables.pojos.Something>> findManyBySomejsonarray(Collection<JsonArray> values, int limit) {
                return findManyByCondition(Something.SOMETHING.SOMEJSONARRAY.in(values),limit);
        }

        /**
     * Find records that have <code>SOMETIMESTAMP IN (values)</code> asynchronously
     */
        public Single<List<generated.rx.jdbc.regular.vertx.tables.pojos.Something>> findManyBySometimestamp(Collection<LocalDateTime> values) {
                return findManyByCondition(Something.SOMETHING.SOMETIMESTAMP.in(values));
        }

        /**
     * Find records that have <code>SOMETIMESTAMP IN (values)</code> asynchronously limited by the given limit
     */
        public Single<List<generated.rx.jdbc.regular.vertx.tables.pojos.Something>> findManyBySometimestamp(Collection<LocalDateTime> values, int limit) {
                return findManyByCondition(Something.SOMETHING.SOMETIMESTAMP.in(values),limit);
        }

        @Override
        public JDBCRXQueryExecutor<SomethingRecord,generated.rx.jdbc.regular.vertx.tables.pojos.Something,Integer> queryExecutor(){
                return (JDBCRXQueryExecutor<SomethingRecord,generated.rx.jdbc.regular.vertx.tables.pojos.Something,Integer>) super.queryExecutor();
        }
}
