/*
 * This file is generated by jOOQ.
 */
package generated.classic.reactive.regular.tables.records;


import generated.classic.reactive.regular.tables.Somethingcomposite;
import generated.classic.reactive.regular.tables.interfaces.ISomethingcomposite;

import io.github.jklingsporn.vertx.jooq.shared.internal.VertxPojo;
import io.vertx.core.json.JsonObject;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;


import static io.github.jklingsporn.vertx.jooq.shared.internal.VertxPojo.*;
/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SomethingcompositeRecord extends UpdatableRecordImpl<SomethingcompositeRecord> implements VertxPojo, Record3<Integer, Integer, JsonObject>, ISomethingcomposite {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>vertx.somethingComposite.someId</code>.
     */
    @Override
    public SomethingcompositeRecord setSomeid(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>vertx.somethingComposite.someId</code>.
     */
    @Override
    public Integer getSomeid() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>vertx.somethingComposite.someSecondId</code>.
     */
    @Override
    public SomethingcompositeRecord setSomesecondid(Integer value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>vertx.somethingComposite.someSecondId</code>.
     */
    @Override
    public Integer getSomesecondid() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>vertx.somethingComposite.someJsonObject</code>.
     */
    @Override
    public SomethingcompositeRecord setSomejsonobject(JsonObject value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>vertx.somethingComposite.someJsonObject</code>.
     */
    @Override
    public JsonObject getSomejsonobject() {
        return (JsonObject) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record2<Integer, Integer> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<Integer, Integer, JsonObject> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<Integer, Integer, JsonObject> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Somethingcomposite.SOMETHINGCOMPOSITE.SOMEID;
    }

    @Override
    public Field<Integer> field2() {
        return Somethingcomposite.SOMETHINGCOMPOSITE.SOMESECONDID;
    }

    @Override
    public Field<JsonObject> field3() {
        return Somethingcomposite.SOMETHINGCOMPOSITE.SOMEJSONOBJECT;
    }

    @Override
    public Integer component1() {
        return getSomeid();
    }

    @Override
    public Integer component2() {
        return getSomesecondid();
    }

    @Override
    public JsonObject component3() {
        return getSomejsonobject();
    }

    @Override
    public Integer value1() {
        return getSomeid();
    }

    @Override
    public Integer value2() {
        return getSomesecondid();
    }

    @Override
    public JsonObject value3() {
        return getSomejsonobject();
    }

    @Override
    public SomethingcompositeRecord value1(Integer value) {
        setSomeid(value);
        return this;
    }

    @Override
    public SomethingcompositeRecord value2(Integer value) {
        setSomesecondid(value);
        return this;
    }

    @Override
    public SomethingcompositeRecord value3(JsonObject value) {
        setSomejsonobject(value);
        return this;
    }

    @Override
    public SomethingcompositeRecord values(Integer value1, Integer value2, JsonObject value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(ISomethingcomposite from) {
        setSomeid(from.getSomeid());
        setSomesecondid(from.getSomesecondid());
        setSomejsonobject(from.getSomejsonobject());
    }

    @Override
    public <E extends ISomethingcomposite> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached SomethingcompositeRecord
     */
    public SomethingcompositeRecord() {
        super(Somethingcomposite.SOMETHINGCOMPOSITE);
    }

    /**
     * Create a detached, initialised SomethingcompositeRecord
     */
    public SomethingcompositeRecord(Integer someid, Integer somesecondid, JsonObject somejsonobject) {
        super(Somethingcomposite.SOMETHINGCOMPOSITE);

        setSomeid(someid);
        setSomesecondid(somesecondid);
        setSomejsonobject(somejsonobject);
    }

        public SomethingcompositeRecord(io.vertx.core.json.JsonObject json) {
                this();
                fromJson(json);
        }
}
