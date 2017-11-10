/*
 * This file is generated by jOOQ.
*/
package generated.classic.vertx.vertx.tables.daos;


import generated.classic.vertx.vertx.tables.Something;
import generated.classic.vertx.vertx.tables.records.SomethingRecord;

import io.github.jklingsporn.vertx.jooq.classic.VertxDAO;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


import io.vertx.core.Handler;
import io.vertx.core.AsyncResult;
/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SomethingDao extends DAOImpl<SomethingRecord, generated.classic.vertx.vertx.tables.pojos.Something, Integer> implements VertxDAO<generated.classic.vertx.vertx.tables.records.SomethingRecord,generated.classic.vertx.vertx.tables.pojos.Something,java.lang.Integer> {

    /**
     * Create a new SomethingDao without any configuration
     */
    public SomethingDao() {
        super(Something.SOMETHING, generated.classic.vertx.vertx.tables.pojos.Something.class);
    }

    /**
     * Create a new SomethingDao with an attached configuration
     */
    public SomethingDao(Configuration configuration) {
        super(Something.SOMETHING, generated.classic.vertx.vertx.tables.pojos.Something.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Integer getId(generated.classic.vertx.vertx.tables.pojos.Something object) {
        return object.getSomeid();
    }

    /**
     * Fetch records that have <code>SOMEID IN (values)</code>
     */
    public List<generated.classic.vertx.vertx.tables.pojos.Something> fetchBySomeid(Integer... values) {
        return fetch(Something.SOMETHING.SOMEID, values);
    }

    /**
     * Fetch a unique record that has <code>SOMEID = value</code>
     */
    public generated.classic.vertx.vertx.tables.pojos.Something fetchOneBySomeid(Integer value) {
        return fetchOne(Something.SOMETHING.SOMEID, value);
    }

    /**
     * Fetch records that have <code>SOMESTRING IN (values)</code>
     */
    public List<generated.classic.vertx.vertx.tables.pojos.Something> fetchBySomestring(String... values) {
        return fetch(Something.SOMETHING.SOMESTRING, values);
    }

    /**
     * Fetch records that have <code>SOMEHUGENUMBER IN (values)</code>
     */
    public List<generated.classic.vertx.vertx.tables.pojos.Something> fetchBySomehugenumber(Long... values) {
        return fetch(Something.SOMETHING.SOMEHUGENUMBER, values);
    }

    /**
     * Fetch records that have <code>SOMESMALLNUMBER IN (values)</code>
     */
    public List<generated.classic.vertx.vertx.tables.pojos.Something> fetchBySomesmallnumber(Short... values) {
        return fetch(Something.SOMETHING.SOMESMALLNUMBER, values);
    }

    /**
     * Fetch records that have <code>SOMEREGULARNUMBER IN (values)</code>
     */
    public List<generated.classic.vertx.vertx.tables.pojos.Something> fetchBySomeregularnumber(Integer... values) {
        return fetch(Something.SOMETHING.SOMEREGULARNUMBER, values);
    }

    /**
     * Fetch records that have <code>SOMEBOOLEAN IN (values)</code>
     */
    public List<generated.classic.vertx.vertx.tables.pojos.Something> fetchBySomeboolean(Boolean... values) {
        return fetch(Something.SOMETHING.SOMEBOOLEAN, values);
    }

    /**
     * Fetch records that have <code>SOMEDOUBLE IN (values)</code>
     */
    public List<generated.classic.vertx.vertx.tables.pojos.Something> fetchBySomedouble(Double... values) {
        return fetch(Something.SOMETHING.SOMEDOUBLE, values);
    }

    /**
     * Fetch records that have <code>SOMEJSONOBJECT IN (values)</code>
     */
    public List<generated.classic.vertx.vertx.tables.pojos.Something> fetchBySomejsonobject(JsonObject... values) {
        return fetch(Something.SOMETHING.SOMEJSONOBJECT, values);
    }

    /**
     * Fetch records that have <code>SOMEJSONARRAY IN (values)</code>
     */
    public List<generated.classic.vertx.vertx.tables.pojos.Something> fetchBySomejsonarray(JsonArray... values) {
        return fetch(Something.SOMETHING.SOMEJSONARRAY, values);
    }

    /**
     * Fetch records that have <code>SOMEID IN (values)</code> asynchronously
     */
    public void fetchBySomeidAsync(List<Integer> values,Handler<AsyncResult<List<generated.classic.vertx.vertx.tables.pojos.Something>>> resultHandler) {
        fetchAsync(Something.SOMETHING.SOMEID,values,resultHandler);
    }

    /**
     * Fetch a unique record that has <code>SOMEID = value</code> asynchronously
     */
    public void fetchOneBySomeidAsync(Integer value,Handler<AsyncResult<generated.classic.vertx.vertx.tables.pojos.Something>> resultHandler) {
        vertx().executeBlocking(h->h.complete(fetchOneBySomeid(value)),resultHandler);
    }

    /**
     * Fetch records that have <code>SOMESTRING IN (values)</code> asynchronously
     */
    public void fetchBySomestringAsync(List<String> values,Handler<AsyncResult<List<generated.classic.vertx.vertx.tables.pojos.Something>>> resultHandler) {
        fetchAsync(Something.SOMETHING.SOMESTRING,values,resultHandler);
    }

    /**
     * Fetch records that have <code>SOMEHUGENUMBER IN (values)</code> asynchronously
     */
    public void fetchBySomehugenumberAsync(List<Long> values,Handler<AsyncResult<List<generated.classic.vertx.vertx.tables.pojos.Something>>> resultHandler) {
        fetchAsync(Something.SOMETHING.SOMEHUGENUMBER,values,resultHandler);
    }

    /**
     * Fetch records that have <code>SOMESMALLNUMBER IN (values)</code> asynchronously
     */
    public void fetchBySomesmallnumberAsync(List<Short> values,Handler<AsyncResult<List<generated.classic.vertx.vertx.tables.pojos.Something>>> resultHandler) {
        fetchAsync(Something.SOMETHING.SOMESMALLNUMBER,values,resultHandler);
    }

    /**
     * Fetch records that have <code>SOMEREGULARNUMBER IN (values)</code> asynchronously
     */
    public void fetchBySomeregularnumberAsync(List<Integer> values,Handler<AsyncResult<List<generated.classic.vertx.vertx.tables.pojos.Something>>> resultHandler) {
        fetchAsync(Something.SOMETHING.SOMEREGULARNUMBER,values,resultHandler);
    }

    /**
     * Fetch records that have <code>SOMEBOOLEAN IN (values)</code> asynchronously
     */
    public void fetchBySomebooleanAsync(List<Boolean> values,Handler<AsyncResult<List<generated.classic.vertx.vertx.tables.pojos.Something>>> resultHandler) {
        fetchAsync(Something.SOMETHING.SOMEBOOLEAN,values,resultHandler);
    }

    /**
     * Fetch records that have <code>SOMEDOUBLE IN (values)</code> asynchronously
     */
    public void fetchBySomedoubleAsync(List<Double> values,Handler<AsyncResult<List<generated.classic.vertx.vertx.tables.pojos.Something>>> resultHandler) {
        fetchAsync(Something.SOMETHING.SOMEDOUBLE,values,resultHandler);
    }

    /**
     * Fetch records that have <code>SOMEJSONOBJECT IN (values)</code> asynchronously
     */
    public void fetchBySomejsonobjectAsync(List<JsonObject> values,Handler<AsyncResult<List<generated.classic.vertx.vertx.tables.pojos.Something>>> resultHandler) {
        fetchAsync(Something.SOMETHING.SOMEJSONOBJECT,values,resultHandler);
    }

    /**
     * Fetch records that have <code>SOMEJSONARRAY IN (values)</code> asynchronously
     */
    public void fetchBySomejsonarrayAsync(List<JsonArray> values,Handler<AsyncResult<List<generated.classic.vertx.vertx.tables.pojos.Something>>> resultHandler) {
        fetchAsync(Something.SOMETHING.SOMEJSONARRAY,values,resultHandler);
    }

    private io.vertx.core.Vertx vertx;

    @Override
    public void setVertx(io.vertx.core.Vertx vertx) {
        this.vertx = vertx;
    }

    @Override
    public io.vertx.core.Vertx vertx() {
        return this.vertx;
    }

}
