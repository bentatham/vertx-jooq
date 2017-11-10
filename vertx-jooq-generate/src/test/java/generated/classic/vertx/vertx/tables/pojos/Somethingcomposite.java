/*
 * This file is generated by jOOQ.
*/
package generated.classic.vertx.vertx.tables.pojos;


import generated.classic.vertx.vertx.tables.interfaces.ISomethingcomposite;

import io.vertx.core.json.JsonObject;

import javax.annotation.Generated;


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
public class Somethingcomposite implements ISomethingcomposite {

    private static final long serialVersionUID = 2141100032;

    private Integer    someid;
    private Integer    somesecondid;
    private JsonObject somejsonobject;

    public Somethingcomposite() {}

    public Somethingcomposite(Somethingcomposite value) {
        this.someid = value.someid;
        this.somesecondid = value.somesecondid;
        this.somejsonobject = value.somejsonobject;
    }

    public Somethingcomposite(
        Integer    someid,
        Integer    somesecondid,
        JsonObject somejsonobject
    ) {
        this.someid = someid;
        this.somesecondid = somesecondid;
        this.somejsonobject = somejsonobject;
    }

    @Override
    public Integer getSomeid() {
        return this.someid;
    }

    @Override
    public Somethingcomposite setSomeid(Integer someid) {
        this.someid = someid;
        return this;
    }

    @Override
    public Integer getSomesecondid() {
        return this.somesecondid;
    }

    @Override
    public Somethingcomposite setSomesecondid(Integer somesecondid) {
        this.somesecondid = somesecondid;
        return this;
    }

    @Override
    public JsonObject getSomejsonobject() {
        return this.somejsonobject;
    }

    @Override
    public Somethingcomposite setSomejsonobject(JsonObject somejsonobject) {
        this.somejsonobject = somejsonobject;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Somethingcomposite (");

        sb.append(someid);
        sb.append(", ").append(somesecondid);
        sb.append(", ").append(somejsonobject);

        sb.append(")");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void from(ISomethingcomposite from) {
        setSomeid(from.getSomeid());
        setSomesecondid(from.getSomesecondid());
        setSomejsonobject(from.getSomejsonobject());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends ISomethingcomposite> E into(E into) {
        into.from(this);
        return into;
    }

    public Somethingcomposite(io.vertx.core.json.JsonObject json) {
        fromJson(json);
    }
}
