/*
 * This file is generated by jOOQ.
 */
package generated.rx.reactive.regular.enums;


import generated.rx.reactive.regular.Vertx;

import org.jooq.Catalog;
import org.jooq.EnumType;
import org.jooq.Schema;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public enum Someenum implements EnumType {

    FOO("FOO"),

    BAR("BAR"),

    BAZ("BAZ");

    private final String literal;

    private Someenum(String literal) {
        this.literal = literal;
    }

    @Override
    public Catalog getCatalog() {
        return getSchema().getCatalog();
    }

    @Override
    public Schema getSchema() {
        return Vertx.VERTX;
    }

    @Override
    public String getName() {
        return "someEnum";
    }

    @Override
    public String getLiteral() {
        return literal;
    }
}
