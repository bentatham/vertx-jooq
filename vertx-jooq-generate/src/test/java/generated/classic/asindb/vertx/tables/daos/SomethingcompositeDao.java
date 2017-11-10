/*
 * This file is generated by jOOQ.
*/
package generated.classic.asindb.vertx.tables.daos;


import generated.classic.asindb.vertx.tables.Somethingcomposite;
import generated.classic.asindb.vertx.tables.records.SomethingcompositeRecord;

import io.github.jklingsporn.vertx.jooq.classic.VertxDAO;
import io.vertx.core.json.JsonObject;

import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.Record2;
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
public class SomethingcompositeDao extends DAOImpl<SomethingcompositeRecord, generated.classic.asindb.vertx.tables.pojos.Somethingcomposite, Record2<Integer, Integer>> implements VertxDAO<generated.classic.asindb.vertx.tables.records.SomethingcompositeRecord,generated.classic.asindb.vertx.tables.pojos.Somethingcomposite,org.jooq.Record2<java.lang.Integer, java.lang.Integer>> {

    /**
     * Create a new SomethingcompositeDao without any configuration
     */
    public SomethingcompositeDao() {
        super(Somethingcomposite.SOMETHINGCOMPOSITE, generated.classic.asindb.vertx.tables.pojos.Somethingcomposite.class);
    }

    /**
     * Create a new SomethingcompositeDao with an attached configuration
     */
    public SomethingcompositeDao(Configuration configuration) {
        super(Somethingcomposite.SOMETHINGCOMPOSITE, generated.classic.asindb.vertx.tables.pojos.Somethingcomposite.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Record2<Integer, Integer> getId(generated.classic.asindb.vertx.tables.pojos.Somethingcomposite object) {
        return compositeKeyRecord(object.getSomeid(), object.getSomesecondid());
    }

    /**
     * Fetch records that have <code>SOMEID IN (values)</code>
     */
    public List<generated.classic.asindb.vertx.tables.pojos.Somethingcomposite> fetchBySomeid(Integer... values) {
        return fetch(Somethingcomposite.SOMETHINGCOMPOSITE.SOMEID, values);
    }

    /**
     * Fetch records that have <code>SOMESECONDID IN (values)</code>
     */
    public List<generated.classic.asindb.vertx.tables.pojos.Somethingcomposite> fetchBySomesecondid(Integer... values) {
        return fetch(Somethingcomposite.SOMETHINGCOMPOSITE.SOMESECONDID, values);
    }

    /**
     * Fetch records that have <code>SOMEJSONOBJECT IN (values)</code>
     */
    public List<generated.classic.asindb.vertx.tables.pojos.Somethingcomposite> fetchBySomejsonobject(JsonObject... values) {
        return fetch(Somethingcomposite.SOMETHINGCOMPOSITE.SOMEJSONOBJECT, values);
    }

    /**
     * Fetch records that have <code>SOMEID IN (values)</code> asynchronously
     */
    public void fetchBySomeidAsync(List<Integer> values,Handler<AsyncResult<List<generated.classic.asindb.vertx.tables.pojos.Somethingcomposite>>> resultHandler) {
        fetchAsync(Somethingcomposite.SOMETHINGCOMPOSITE.SOMEID,values,resultHandler);
    }

    /**
     * Fetch records that have <code>SOMESECONDID IN (values)</code> asynchronously
     */
    public void fetchBySomesecondidAsync(List<Integer> values,Handler<AsyncResult<List<generated.classic.asindb.vertx.tables.pojos.Somethingcomposite>>> resultHandler) {
        fetchAsync(Somethingcomposite.SOMETHINGCOMPOSITE.SOMESECONDID,values,resultHandler);
    }

    /**
     * Fetch records that have <code>SOMEJSONOBJECT IN (values)</code> asynchronously
     */
    public void fetchBySomejsonobjectAsync(List<JsonObject> values,Handler<AsyncResult<List<generated.classic.asindb.vertx.tables.pojos.Somethingcomposite>>> resultHandler) {
        fetchAsync(Somethingcomposite.SOMETHINGCOMPOSITE.SOMEJSONOBJECT,values,resultHandler);
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
