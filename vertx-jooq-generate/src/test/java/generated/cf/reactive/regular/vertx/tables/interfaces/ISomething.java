/*
 * This file is generated by jOOQ.
 */
package generated.cf.reactive.regular.vertx.tables.interfaces;


import generated.cf.reactive.regular.vertx.enums.Someenum;

import io.github.jklingsporn.vertx.jooq.generate.converter.SomeJsonPojo;
import io.github.jklingsporn.vertx.jooq.shared.UnexpectedJsonValueType;
import io.github.jklingsporn.vertx.jooq.shared.internal.VertxPojo;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;


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
    public ISomething setSomeenum(Someenum value);

    /**
     * Getter for <code>vertx.something.someEnum</code>.
     */
    public Someenum getSomeenum();

    /**
     * Setter for <code>vertx.something.someJsonObject</code>.
     */
    public ISomething setSomejsonobject(JsonObject value);

    /**
     * Getter for <code>vertx.something.someJsonObject</code>.
     */
    public JsonObject getSomejsonobject();

    /**
     * Setter for <code>vertx.something.someCustomJsonObject</code>.
     */
    public ISomething setSomecustomjsonobject(SomeJsonPojo value);

    /**
     * Getter for <code>vertx.something.someCustomJsonObject</code>.
     */
    public SomeJsonPojo getSomecustomjsonobject();

    /**
     * Setter for <code>vertx.something.someJsonArray</code>.
     */
    public ISomething setSomejsonarray(JsonArray value);

    /**
     * Getter for <code>vertx.something.someJsonArray</code>.
     */
    public JsonArray getSomejsonarray();

    /**
     * Setter for <code>vertx.something.someVertxJsonObject</code>.
     */
    public ISomething setSomevertxjsonobject(JsonObject value);

    /**
     * Getter for <code>vertx.something.someVertxJsonObject</code>.
     */
    public JsonObject getSomevertxjsonobject();

    /**
     * Setter for <code>vertx.something.someTime</code>.
     */
    public ISomething setSometime(LocalTime value);

    /**
     * Getter for <code>vertx.something.someTime</code>.
     */
    public LocalTime getSometime();

    /**
     * Setter for <code>vertx.something.someDate</code>.
     */
    public ISomething setSomedate(LocalDate value);

    /**
     * Getter for <code>vertx.something.someDate</code>.
     */
    public LocalDate getSomedate();

    /**
     * Setter for <code>vertx.something.someTimestamp</code>.
     */
    public ISomething setSometimestamp(LocalDateTime value);

    /**
     * Getter for <code>vertx.something.someTimestamp</code>.
     */
    public LocalDateTime getSometimestamp();

    /**
     * Setter for <code>vertx.something.someTimestampWithTZ</code>.
     */
    public ISomething setSometimestampwithtz(OffsetDateTime value);

    /**
     * Getter for <code>vertx.something.someTimestampWithTZ</code>.
     */
    public OffsetDateTime getSometimestampwithtz();

    /**
     * Setter for <code>vertx.something.someByteA</code>.
     */
    public ISomething setSomebytea(byte[] value);

    /**
     * Getter for <code>vertx.something.someByteA</code>.
     */
    public byte[] getSomebytea();

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
        try {
            setSomeid(json.getInteger("someId"));
        } catch (java.lang.ClassCastException e) {
            throw new UnexpectedJsonValueType("someId","java.lang.Integer",e);
        }
        try {
            setSomestring(json.getString("someString"));
        } catch (java.lang.ClassCastException e) {
            throw new UnexpectedJsonValueType("someString","java.lang.String",e);
        }
        try {
            setSomehugenumber(json.getLong("someHugeNumber"));
        } catch (java.lang.ClassCastException e) {
            throw new UnexpectedJsonValueType("someHugeNumber","java.lang.Long",e);
        }
        try {
            setSomesmallnumber(json.getInteger("someSmallNumber")==null?null:json.getInteger("someSmallNumber").shortValue());
        } catch (java.lang.ClassCastException e) {
            throw new UnexpectedJsonValueType("someSmallNumber","java.lang.Short",e);
        }
        try {
            setSomeregularnumber(json.getInteger("someRegularNumber"));
        } catch (java.lang.ClassCastException e) {
            throw new UnexpectedJsonValueType("someRegularNumber","java.lang.Integer",e);
        }
        try {
            setSomedouble(json.getDouble("someDouble"));
        } catch (java.lang.ClassCastException e) {
            throw new UnexpectedJsonValueType("someDouble","java.lang.Double",e);
        }
        try {
            setSomeenum(java.util.Arrays.stream(generated.cf.reactive.regular.vertx.enums.Someenum.values()).filter(td -> td.getLiteral().equals(json.getString("someEnum"))).findFirst().orElse(null));
        } catch (java.lang.ClassCastException e) {
            throw new UnexpectedJsonValueType("someEnum","generated.cf.reactive.regular.vertx.enums.Someenum",e);
        }
        try {
            setSomejsonobject(json.getJsonObject("someJsonObject"));
        } catch (java.lang.ClassCastException e) {
            throw new UnexpectedJsonValueType("someJsonObject","io.vertx.core.json.JsonObject",e);
        }
        try {
            setSomecustomjsonobject(generated.cf.reactive.regular.vertx.tables.converters.Converters.IO_GITHUB_JKLINGSPORN_VERTX_JOOQ_GENERATE_CONVERTER_SOMEJSONPOJOCONVERTER_INSTANCE.pgConverter().from(json.getJsonObject("someCustomJsonObject")));
        } catch (java.lang.ClassCastException e) {
            throw new UnexpectedJsonValueType("someCustomJsonObject","io.github.jklingsporn.vertx.jooq.generate.converter.SomeJsonPojo",e);
        }
        try {
            setSomejsonarray(json.getJsonArray("someJsonArray"));
        } catch (java.lang.ClassCastException e) {
            throw new UnexpectedJsonValueType("someJsonArray","io.vertx.core.json.JsonArray",e);
        }
        try {
            setSomevertxjsonobject(generated.cf.reactive.regular.vertx.tables.converters.Converters.IO_GITHUB_JKLINGSPORN_VERTX_JOOQ_SHARED_POSTGRES_JSONBTOJSONOBJECTCONVERTER_INSTANCE.pgConverter().from(json.getJsonObject("someVertxJsonObject")));
        } catch (java.lang.ClassCastException e) {
            throw new UnexpectedJsonValueType("someVertxJsonObject","io.vertx.core.json.JsonObject",e);
        }
        try {
            String someTimeString = json.getString("someTime");
            setSometime(someTimeString == null?null:java.time.LocalTime.parse(someTimeString));
        } catch (java.lang.ClassCastException e) {
            throw new UnexpectedJsonValueType("someTime","java.time.LocalTime",e);
        }
        try {
            String someDateString = json.getString("someDate");
            setSomedate(someDateString == null?null:java.time.LocalDate.parse(someDateString));
        } catch (java.lang.ClassCastException e) {
            throw new UnexpectedJsonValueType("someDate","java.time.LocalDate",e);
        }
        try {
            String someTimestampString = json.getString("someTimestamp");
            setSometimestamp(someTimestampString == null?null:java.time.LocalDateTime.parse(someTimestampString));
        } catch (java.lang.ClassCastException e) {
            throw new UnexpectedJsonValueType("someTimestamp","java.time.LocalDateTime",e);
        }
        try {
            String someTimestampWithTZString = json.getString("someTimestampWithTZ");
            setSometimestampwithtz(someTimestampWithTZString == null?null:java.time.OffsetDateTime.parse(someTimestampWithTZString));
        } catch (java.lang.ClassCastException e) {
            throw new UnexpectedJsonValueType("someTimestampWithTZ","java.time.OffsetDateTime",e);
        }
        try {
            setSomebytea(json.getBinary("someByteA"));
        } catch (java.lang.ClassCastException e) {
            throw new UnexpectedJsonValueType("someByteA","byte[]",e);
        }
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
        json.put("someCustomJsonObject",generated.cf.reactive.regular.vertx.tables.converters.Converters.IO_GITHUB_JKLINGSPORN_VERTX_JOOQ_GENERATE_CONVERTER_SOMEJSONPOJOCONVERTER_INSTANCE.pgConverter().to(getSomecustomjsonobject()));
        json.put("someJsonArray",getSomejsonarray());
        json.put("someVertxJsonObject",generated.cf.reactive.regular.vertx.tables.converters.Converters.IO_GITHUB_JKLINGSPORN_VERTX_JOOQ_SHARED_POSTGRES_JSONBTOJSONOBJECTCONVERTER_INSTANCE.pgConverter().to(getSomevertxjsonobject()));
        json.put("someTime",getSometime()==null?null:getSometime().toString());
        json.put("someDate",getSomedate()==null?null:getSomedate().toString());
        json.put("someTimestamp",getSometimestamp()==null?null:getSometimestamp().toString());
        json.put("someTimestampWithTZ",getSometimestampwithtz()==null?null:getSometimestampwithtz().toString());
        json.put("someByteA",getSomebytea());
        return json;
    }

}
