/*
 * This file is generated by jOOQ.
 */
package generated.classic.reactive.mysql.tables.interfaces;


import generated.classic.reactive.mysql.enums.SomethingSomeenum;

import io.github.jklingsporn.vertx.jooq.shared.internal.VertxPojo;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.io.Serializable;
import java.time.LocalDateTime;


import static io.github.jklingsporn.vertx.jooq.shared.internal.VertxPojo.*;
/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public interface ISomething extends VertxPojo, Serializable {

    /**
     * Setter for <code>vertx.something.someId</code>.
     */
    public ISomething setSomeid(Integer value);

    /**
     * Getter for <code>vertx.something.someId</code>.
     */
    public Integer getSomeid();

    /**
     * Setter for <code>vertx.something.someString</code>.
     */
    public ISomething setSomestring(String value);

    /**
     * Getter for <code>vertx.something.someString</code>.
     */
    public String getSomestring();

    /**
     * Setter for <code>vertx.something.someHugeNumber</code>.
     */
    public ISomething setSomehugenumber(Long value);

    /**
     * Getter for <code>vertx.something.someHugeNumber</code>.
     */
    public Long getSomehugenumber();

    /**
     * Setter for <code>vertx.something.someSmallNumber</code>.
     */
    public ISomething setSomesmallnumber(Short value);

    /**
     * Getter for <code>vertx.something.someSmallNumber</code>.
     */
    public Short getSomesmallnumber();

    /**
     * Setter for <code>vertx.something.someRegularNumber</code>.
     */
    public ISomething setSomeregularnumber(Integer value);

    /**
     * Getter for <code>vertx.something.someRegularNumber</code>.
     */
    public Integer getSomeregularnumber();

    /**
     * Setter for <code>vertx.something.someDouble</code>.
     */
    public ISomething setSomedouble(Double value);

    /**
     * Getter for <code>vertx.something.someDouble</code>.
     */
    public Double getSomedouble();

    /**
     * Setter for <code>vertx.something.someEnum</code>.
     */
    public ISomething setSomeenum(SomethingSomeenum value);

    /**
     * Getter for <code>vertx.something.someEnum</code>.
     */
    public SomethingSomeenum getSomeenum();

    /**
     * Setter for <code>vertx.something.someJsonObject</code>.
     */
    public ISomething setSomejsonobject(JsonObject value);

    /**
     * Getter for <code>vertx.something.someJsonObject</code>.
     */
    public JsonObject getSomejsonobject();

    /**
     * Setter for <code>vertx.something.someJsonArray</code>.
     */
    public ISomething setSomejsonarray(JsonArray value);

    /**
     * Getter for <code>vertx.something.someJsonArray</code>.
     */
    public JsonArray getSomejsonarray();

    /**
     * Setter for <code>vertx.something.someTimestamp</code>.
     */
    public ISomething setSometimestamp(LocalDateTime value);

    /**
     * Getter for <code>vertx.something.someTimestamp</code>.
     */
    public LocalDateTime getSometimestamp();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common interface ISomething
     */
    public void from(ISomething from);

    /**
     * Copy data into another generated Record/POJO implementing the common interface ISomething
     */
    public <E extends ISomething> E into(E into);

        @Override
        public default ISomething fromJson(io.vertx.core.json.JsonObject json) {
                setOrThrow(this::setSomeid,json::getInteger,"someId","java.lang.Integer");
                setOrThrow(this::setSomestring,json::getString,"someString","java.lang.String");
                setOrThrow(this::setSomehugenumber,json::getLong,"someHugeNumber","java.lang.Long");
                setOrThrow(this::setSomesmallnumber,key -> {Integer i = json.getInteger(key); return i==null?null:i.shortValue();},"someSmallNumber","java.lang.Short");
                setOrThrow(this::setSomeregularnumber,json::getInteger,"someRegularNumber","java.lang.Integer");
                setOrThrow(this::setSomedouble,json::getDouble,"someDouble","java.lang.Double");
                setOrThrow(this::setSomeenum,key -> java.util.Arrays.stream(generated.classic.reactive.mysql.enums.SomethingSomeenum.values()).filter(td -> td.getLiteral().equals(json.getString(key))).findFirst().orElse(null),"someEnum","generated.classic.reactive.mysql.enums.SomethingSomeenum");
                setOrThrow(this::setSomejsonobject,json::getJsonObject,"someJsonObject","io.vertx.core.json.JsonObject");
                setOrThrow(this::setSomejsonarray,json::getJsonArray,"someJsonArray","io.vertx.core.json.JsonArray");
                setOrThrow(this::setSometimestamp,key -> {String s = json.getString(key); return s==null?null:java.time.LocalDateTime.parse(s);},"someTimestamp","java.time.LocalDateTime");
                return this;
        }


        @Override
        public default io.vertx.core.json.JsonObject toJson() {
                io.vertx.core.json.JsonObject json = new io.vertx.core.json.JsonObject();
                json.put("someId",getSomeid());
                json.put("someString",getSomestring());
                json.put("someHugeNumber",getSomehugenumber());
                json.put("someSmallNumber",getSomesmallnumber());
                json.put("someRegularNumber",getSomeregularnumber());
                json.put("someDouble",getSomedouble());
                json.put("someEnum",getSomeenum()==null?null:getSomeenum().getLiteral());
                json.put("someJsonObject",getSomejsonobject());
                json.put("someJsonArray",getSomejsonarray());
                json.put("someTimestamp",getSometimestamp()==null?null:getSometimestamp().toString());
                return json;
        }

}
