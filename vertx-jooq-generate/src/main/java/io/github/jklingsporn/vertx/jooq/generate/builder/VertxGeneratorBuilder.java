package io.github.jklingsporn.vertx.jooq.generate.builder;

import io.github.jklingsporn.vertx.jooq.shared.JsonArrayConverter;
import io.github.jklingsporn.vertx.jooq.shared.JsonObjectConverter;
import io.github.jklingsporn.vertx.jooq.shared.internal.AbstractVertxDAO;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.jooq.Configuration;
import org.jooq.util.*;

import java.io.File;
import java.math.BigDecimal;
import java.time.*;
import java.time.temporal.Temporal;
import java.util.*;
import java.util.function.Function;

/**
 * Builder to create a {@code VertxGenerator}. Non-instantiable, see static init() method.
 * @author jensklingsporn
 */
public class VertxGeneratorBuilder {

    static final Map<String,String> SUPPORTED_MYSQL_INSERT_RETURNING_TYPES_MAP;
    static{
        SUPPORTED_MYSQL_INSERT_RETURNING_TYPES_MAP = new HashMap<>();
        SUPPORTED_MYSQL_INSERT_RETURNING_TYPES_MAP.put(Byte.class.getSimpleName(), byte.class.getSimpleName());
        SUPPORTED_MYSQL_INSERT_RETURNING_TYPES_MAP.put(Short.class.getSimpleName(), short.class.getSimpleName());
        SUPPORTED_MYSQL_INSERT_RETURNING_TYPES_MAP.put(Integer.class.getSimpleName(), int.class.getSimpleName());
        SUPPORTED_MYSQL_INSERT_RETURNING_TYPES_MAP.put(Long.class.getSimpleName(), long.class.getSimpleName());
    }

    enum APIType{
        CLASSIC,
        COMPLETABLE_FUTURE,
        RX;
    }


    private VertxGeneratorBuilder() {}

    /**
     * @return an {@code APIStep} to init the build of a {@code VertxGeneratorStrategy}.
     */
    public static APIStep init(){
        return new APIStepImpl(new ComponentBasedVertxGenerator()
                .setRenderFQVertxNameDelegate(() -> "io.vertx.core.Vertx"));
    }

    static class APIStepImpl implements APIStep {

        private final ComponentBasedVertxGenerator base;

        APIStepImpl(ComponentBasedVertxGenerator base) {
            this.base = base;
            this.base.addOverwriteDAODelegate((out, className, tableIdentifier, tableRecord, pType, tType) -> {
                out.println();
                out.tab(1).override();
                out.tab(1).println("public %s queryExecutor(){", base.renderQueryExecutor(tableRecord, pType, tType));
                out.tab(2).println("return (%s) super.queryExecutor();", base.renderQueryExecutor(tableRecord, pType, tType));
                out.tab(1).println("}");
            });
        }

        @Override
        public ExecutionStep withClassicAPI() {
            return new ExecutionStepImpl(base
                    .setApiType(APIType.CLASSIC)
                    .setWriteDAOImportsDelegate(out -> out.println("import io.vertx.core.Future;"))
                    .setRenderQueryExecutorTypesDelegate(new RenderQueryExecutorTypesComponent() {
                        @Override
                        public String renderFindOneType(String pType) {
                            return String.format("Future<%s>", pType);
                        }

                        @Override
                        public String renderFindManyType(String pType) {
                            return String.format("Future<List<%s>>", pType);
                        }

                        @Override
                        public String renderExecType() {
                            return "Future<Integer>";
                        }

                        @Override
                        public String renderInsertReturningType(String tType) {
                            return String.format("Future<%s>", tType);
                        }
                    })
                    .setRenderDAOInterfaceDelegate((rType, pType, tType) -> String.format("io.github.jklingsporn.vertx.jooq.classic.VertxDAO<%s,%s,%s>", rType, pType, tType))
            );
        }

        @Override
        public ExecutionStep withCompletableFutureAPI() {
            return new ExecutionStepImpl(base
                    .setApiType(APIType.COMPLETABLE_FUTURE)
                    .setWriteDAOImportsDelegate(out -> {
                        out.println("import java.util.concurrent.CompletableFuture;");
                        out.println("import io.github.jklingsporn.vertx.jooq.completablefuture.VertxDAO;");
                    })
                    .setRenderQueryExecutorTypesDelegate(new RenderQueryExecutorTypesComponent() {
                        @Override
                        public String renderFindOneType(String pType) {
                            return String.format("CompletableFuture<%s>", pType);
                        }

                        @Override
                        public String renderFindManyType(String pType) {
                            return String.format("CompletableFuture<List<%s>>", pType);
                        }

                        @Override
                        public String renderExecType() {
                            return "CompletableFuture<Integer>";
                        }

                        @Override
                        public String renderInsertReturningType(String tType) {
                            return String.format("CompletableFuture<%s>", tType);
                        }
                    })
                    .setRenderDAOInterfaceDelegate((rType, pType, tType) -> String.format("io.github.jklingsporn.vertx.jooq.completablefuture.VertxDAO<%s,%s,%s>", rType, pType, tType))
            );
        }

        @Override
        public ExecutionStep withRXAPI() {
            return new ExecutionStepImpl(base
                    .setRenderFQVertxNameDelegate(() -> "io.vertx.reactivex.core.Vertx")
                    .setApiType(APIType.RX)
                    .setWriteDAOImportsDelegate(out -> {
                        out.println("import io.reactivex.Single;");
                        out.println("import java.util.Optional;");
                    })
                    .setRenderQueryExecutorTypesDelegate(new RenderQueryExecutorTypesComponent() {
                        @Override
                        public String renderFindOneType(String pType) {
                            return String.format("Single<Optional<%s>>",pType);
                        }

                        @Override
                        public String renderFindManyType(String pType) {
                            return String.format("Single<List<%s>>",pType);
                        }

                        @Override
                        public String renderExecType() {
                            return "Single<Integer>";
                        }

                        @Override
                        public String renderInsertReturningType(String tType) {
                            return String.format("Single<%s>", tType);
                        }
                    })
                    .setRenderDAOInterfaceDelegate((rType, pType, tType) -> String.format("io.github.jklingsporn.vertx.jooq.rx.VertxDAO<%s,%s,%s>", rType, pType, tType))
            );
        }
    }

    static class ExecutionStepImpl implements ExecutionStep {

        private final ComponentBasedVertxGenerator base;

        ExecutionStepImpl(ComponentBasedVertxGenerator base) {
            this.base = base;
        }

        @Override
        public DIStep withJDBCDriver() {
            base.setRenderDAOExtendsDelegate(AbstractVertxDAO.class::getName);
            switch(base.apiType){
                case CLASSIC:
                    return new DIStepImpl(base
                            .setWriteDAOImportsDelegate(base.writeDAOImportsDelegate.andThen(out -> out.println("import io.github.jklingsporn.vertx.jooq.classic.jdbc.JDBCClassicQueryExecutor;")))
                            .setRenderQueryExecutorDelegate((rType, pType, tType) -> String.format("JDBCClassicQueryExecutor<%s,%s,%s>", rType, pType, tType))
                            .setWriteConstructorDelegate((out, className, tableIdentifier, tableRecord, pType, tType) -> {
                                out.tab(1).javadoc("@param configuration The Configuration used for rendering and query execution.\n     * @param vertx the vertx instance");
                                out.tab(1).println("public %s(%s configuration, %s vertx) {", className, Configuration.class, base.renderFQVertxName());
                                out.tab(2).println("super(%s, %s.class, new %s(configuration,%s.class,vertx));", tableIdentifier, pType, base.renderQueryExecutor(tableRecord, pType, tType), pType);
                                out.tab(1).println("}");
                            })
                    );
                case COMPLETABLE_FUTURE:
                    return new DIStepImpl(base
                            .setWriteDAOImportsDelegate(base.writeDAOImportsDelegate.andThen(out -> out.println("import io.github.jklingsporn.vertx.jooq.completablefuture.jdbc.JDBCCompletableFutureQueryExecutor;")))
                            .setRenderQueryExecutorDelegate((rType, pType, tType) -> String.format("JDBCCompletableFutureQueryExecutor<%s,%s,%s>", rType, pType, tType))
                            .setWriteConstructorDelegate((out, className, tableIdentifier, tableRecord, pType, tType) -> {
                                out.tab(1).javadoc("@param configuration The Configuration used for rendering and query execution.\n     * @param vertx the vertx instance");
                                out.tab(1).println("public %s(%s configuration, %s vertx) {", className, Configuration.class, base.renderFQVertxName());
                                out.tab(2).println("super(%s, %s.class, new %s(configuration,%s.class,vertx));", tableIdentifier, pType, base.renderQueryExecutor(tableRecord, pType, tType), pType);
                                out.tab(1).println("}");
                            })
                    );
                case RX:
                    return new DIStepImpl(base
                            .setWriteDAOImportsDelegate(base.writeDAOImportsDelegate.andThen(out -> out.println("import io.github.jklingsporn.vertx.jooq.rx.jdbc.JDBCRXQueryExecutor;")))
                            .setRenderQueryExecutorDelegate((rType, pType, tType) -> String.format("JDBCRXQueryExecutor<%s,%s,%s>", rType, pType, tType))
                            .setWriteConstructorDelegate((out, className, tableIdentifier, tableRecord, pType, tType) -> {
                                out.tab(1).javadoc("@param configuration The Configuration used for rendering and query execution.\n" +
                                        "     * @param vertx the vertx instance");
                                out.tab(1).println("public %s(%s configuration, %s vertx) {", className, Configuration.class, base.renderFQVertxName());
                                out.tab(2).println("super(%s, %s.class, new %s(configuration,%s.class,vertx));", tableIdentifier, pType, base.renderQueryExecutor(tableRecord, pType, tType),pType);
                                out.tab(1).println("}");
                            })
                    );
                default: throw new UnsupportedOperationException(base.apiType.toString());
            }
        }

        @Override
        public DIStep withAsyncDriver() {
            base.setRenderDAOExtendsDelegate(()->"io.github.jklingsporn.vertx.jooq.shared.async.AbstractAsyncVertxDAO");
            base.addOverwriteDAODelegate((out, className, tableIdentifier, tableRecord, pType, tType) -> {
                if (SUPPORTED_MYSQL_INSERT_RETURNING_TYPES_MAP.containsKey(tType)) {
                    out.println();
                    out.tab(1).override();
                    out.tab(1).println("protected java.util.function.Function<Object,%s> keyConverter(){", tType);
                    out.tab(2).println("return lastId -> %s.valueOf(((%s)lastId).getLong(0).%sValue());", tType, JsonArray.class.getName(), SUPPORTED_MYSQL_INSERT_RETURNING_TYPES_MAP.get(tType));
                    out.tab(1).println("}");
                }
            });
            switch (base.apiType) {
                case CLASSIC:
                    return new DIStepImpl(base
                            .setWriteDAOImportsDelegate(base.writeDAOImportsDelegate.andThen(out -> out.println("import io.github.jklingsporn.vertx.jooq.classic.async.AsyncClassicQueryExecutor;")))
                            .setRenderQueryExecutorDelegate((rType, pType, tType) -> String.format("AsyncClassicQueryExecutor<%s,%s,%s>", rType, pType, tType))
                            .setWriteConstructorDelegate((out, className, tableIdentifier, tableRecord, pType, tType) -> {
                                out.tab(1).javadoc("@param configuration Used for rendering, so only SQLDialect must be set and must be one of the MYSQL types or POSTGRES.\n     * @param delegate A configured AsyncSQLClient that is used for query execution");
                                out.tab(1).println("public %s(%s configuration, io.vertx.ext.asyncsql.AsyncSQLClient delegate) {", className, Configuration.class);
                                out.tab(2).println("super(%s, %s.class, new %s(configuration,delegate,%s::new, %s));", tableIdentifier, pType, base.renderQueryExecutor(tableRecord, pType, tType),pType, tableIdentifier);
                                out.tab(1).println("}");
                            })

                    );
                case COMPLETABLE_FUTURE:
                    return new DIStepImpl(base
                            .setWriteDAOImportsDelegate(base.writeDAOImportsDelegate.andThen(out -> out.println("import io.github.jklingsporn.vertx.jooq.completablefuture.async.AsyncCompletableFutureQueryExecutor;")))
                            .setRenderQueryExecutorDelegate((rType, pType, tType) -> String.format("AsyncCompletableFutureQueryExecutor<%s,%s,%s>", rType, pType, tType))
                            .setWriteConstructorDelegate((out, className, tableIdentifier, tableRecord, pType, tType) -> {
                                out.tab(1).javadoc("@param configuration Used for rendering, so only SQLDialect must be set and must be one of the MYSQL types or POSTGRES.\n" +
                                        "     * @param vertx the vertx instance\n     * @param delegate A configured AsyncSQLClient that is used for query execution");
                                out.tab(1).println("public %s(%s configuration, %s vertx, io.vertx.ext.asyncsql.AsyncSQLClient delegate) {", className, Configuration.class, base.renderFQVertxName());
                                out.tab(2).println("super(%s, %s.class, new %s(configuration,vertx,delegate,%s::new, %s));", tableIdentifier, pType, base.renderQueryExecutor(tableRecord, pType, tType),pType,tableIdentifier);
                                out.tab(1).println("}");
                            })

                    );
                case RX:
                    return new DIStepImpl(base
                            .setWriteDAOImportsDelegate(base.writeDAOImportsDelegate.andThen(out -> out.println("import io.github.jklingsporn.vertx.jooq.rx.async.AsyncRXQueryExecutor;")))
                            .setRenderQueryExecutorDelegate((rType, pType, tType) -> String.format("AsyncRXQueryExecutor<%s,%s,%s>", rType, pType, tType))
                            .setWriteConstructorDelegate((out, className, tableIdentifier, tableRecord, pType, tType) -> {
                                out.tab(1).javadoc("@param configuration Used for rendering, so only SQLDialect must be set and must be one of the MYSQL types or POSTGRES.\n     * @param delegate A configured AsyncSQLClient that is used for query execution");
                                out.tab(1).println("public %s(%s configuration,io.vertx.reactivex.ext.asyncsql.AsyncSQLClient delegate) {", className, Configuration.class);
                                out.tab(2).println("super(%s, %s.class, new %s(configuration,delegate,%s::new, %s));", tableIdentifier, pType, base.renderQueryExecutor(tableRecord, pType, tType),pType,tableIdentifier);
                                out.tab(1).println("}");
                            })

                    );
                default: throw new UnsupportedOperationException(base.apiType.toString());
            }
        }

        @Override
        public DIStep withPostgresReactiveDriver() {
            base.setRenderDAOExtendsDelegate(()->"io.github.jklingspon.vertx.jooq.shared.reactive.AbstractReactiveVertxDAO");
            base.addWriteExtraDataDelegate((schema, writerGen) -> {
                ComponentBasedVertxGenerator.logger.info("Generate RowMappers ... ");
                String packageName = (base.getActiveGenerator().getStrategy().getTargetDirectory() + "/" + base.getActiveGenerator().getStrategy().getJavaPackageName(schema) + ".tables.mappers").replaceAll("\\.", "/");
                File moduleFile = new File(packageName, "RowMappers.java");
                JavaWriter out = writerGen.apply(moduleFile);
                out.println("package " + base.getActiveGenerator().getStrategy().getJavaPackageName(schema) + ".tables.mappers;");
                out.println();
                out.println("import io.reactiverse.pgclient.Row;");
                out.println("import %s;", Function.class.getName());
                out.println();
                out.println("public class RowMappers {");
                out.println();
                out.tab(1).println("private RowMappers(){}"); //not instantiable
                out.println();
                Set<String> supportedRowTypes = new HashSet<>();
                supportedRowTypes.add(Boolean.class.getName());
                supportedRowTypes.add(Integer.class.getName());
                supportedRowTypes.add(Long.class.getName());
                supportedRowTypes.add(Float.class.getName());
                supportedRowTypes.add(Double.class.getName());
                supportedRowTypes.add(BigDecimal.class.getName());
                supportedRowTypes.add(String.class.getName());
                supportedRowTypes.add(Character.class.getName());
                supportedRowTypes.add(Buffer.class.getName());
                supportedRowTypes.add(JsonObject.class.getName());
                supportedRowTypes.add(JsonArray.class.getName());
                supportedRowTypes.add(UUID.class.getName());
                supportedRowTypes.add(Instant.class.getName());
                supportedRowTypes.add(Temporal.class.getName());
                supportedRowTypes.add(LocalTime.class.getName());
                supportedRowTypes.add(LocalDate.class.getName());
                supportedRowTypes.add(LocalDateTime.class.getName());
                supportedRowTypes.add(OffsetTime.class.getName());
                supportedRowTypes.add(OffsetDateTime.class.getName());
                for (TableDefinition table : schema.getTables()) {
                    UniqueKeyDefinition key = table.getPrimaryKey();
                    if (key == null) {
                        ComponentBasedVertxGenerator.logger.info("{} has no primary key. Skipping...", out.file().getName());
                        continue;
                    }
                    final String pType = base.getActiveGenerator().getStrategy().getFullJavaClassName(table, GeneratorStrategy.Mode.POJO);
                    out.tab(1).println("public static Function<Row,%s> get%sMapper() {",pType,base.getActiveGenerator().getStrategy().getJavaClassName(table, GeneratorStrategy.Mode.POJO));
                    out.tab(2).println("return row -> {");
                    out.tab(3).println("%s pojo = new %s();",pType,pType);
                    for (TypedElementDefinition<?> column : table.getColumns()) {
                        String setter = base.getActiveGenerator().getStrategy().getJavaSetterName(column, GeneratorStrategy.Mode.INTERFACE);
                        String javaType = base.getActiveGenerator().getJavaType(column.getType());
                        if(supportedRowTypes.contains(javaType)) {
                            try {
                                out.tab(3).println("pojo.%s(row.get%s(\"%s\"));", setter, Class.forName(javaType).getSimpleName(), column.getName());
                            } catch (ClassNotFoundException e) {
                                ComponentBasedVertxGenerator.logger.error(e.getMessage(), e);
                            }
                        }else if(column.getType().getConverter() != null && column.getType().getConverter().equalsIgnoreCase(JsonObjectConverter.class.getName())){
                            out.tab(3).println("pojo.%s(row.getJsonObject(\"%s\"));", setter, column.getName());
                        }else if(column.getType().getConverter() != null && column.getType().getConverter().equalsIgnoreCase(JsonArrayConverter.class.getName())){
                            out.tab(3).println("pojo.%s(row.getJsonArray(\"%s\"));", setter, column.getName());
                        }else{
                            ComponentBasedVertxGenerator.logger.warn(String.format("Omitting unrecognized type %s (%s) for column %s in table %s!",column.getType(),javaType,column.getName(),table.getName()));
                            out.tab(3).println(String.format("// Omitting unrecognized type %s (%s) for column %s!",column.getType(),javaType, column.getName()));
                        }
                    }
                    out.tab(3).println("return pojo;");
                    out.tab(2).println("};");
                    out.tab(1).println("}");
                    out.println();
                }
                out.println("}");
                return out;
            });
            switch(base.apiType){
                case CLASSIC:
                    return new DIStepImpl(base
                            .setWriteDAOImportsDelegate(base.writeDAOImportsDelegate.andThen(out -> out.println("import io.github.jklingsporn.vertx.jooq.classic.reactivepg.ReactiveClassicQueryExecutor;")))
                            .setRenderQueryExecutorDelegate((rType, pType, tType) -> String.format("ReactiveClassicQueryExecutor<%s,%s,%s>", rType, pType, tType))
                            .setWriteConstructorDelegate((out, className, tableIdentifier, tableRecord, pType, tType) -> {
                                //pType = foo.bar.pojos.Somepojo -> split[0]=foo.bar. -> split[1]=Somepojo
                                String[] split = pType.split("pojos.");
                                String mapperFactory = String.format("%smappers.RowMappers.get%sMapper()",split[0],split[1]);
                                out.tab(1).javadoc("@param configuration Used for rendering, so only SQLDialect must be set and must be one of the POSTGREs types.\n     * @param delegate A configured AsyncSQLClient that is used for query execution");
                                out.tab(1).println("public %s(%s configuration, io.reactiverse.pgclient.PgClient delegate) {", className, Configuration.class);
                                out.tab(2).println("super(%s, %s.class, new %s(configuration,delegate,%s));", tableIdentifier, pType, base.renderQueryExecutor(tableRecord, pType, tType),mapperFactory);
                                out.tab(1).println("}");
                            })

                    );
                case COMPLETABLE_FUTURE:
                    return new DIStepImpl(base
                            .setWriteDAOImportsDelegate(base.writeDAOImportsDelegate.andThen(out -> out.println("import io.github.jklingsporn.vertx.jooq.completablefuture.reactivepg.ReactiveCompletableFutureQueryExecutor;")))
                            .setRenderQueryExecutorDelegate((rType, pType, tType) -> String.format("ReactiveCompletableFutureQueryExecutor<%s,%s,%s>", rType, pType, tType))
                            .setWriteConstructorDelegate((out, className, tableIdentifier, tableRecord, pType, tType) -> {
                                //pType = foo.bar.pojos.Somepojo -> split[0]=foo.bar. -> split[1]=Somepojo
                                String[] split = pType.split("pojos.");
                                String mapperFactory = String.format("%smappers.RowMappers.get%sMapper()",split[0],split[1]);
                                out.tab(1).javadoc("@param configuration The Configuration used for rendering and query execution.\n     * @param vertx the vertx instance");
                                out.tab(1).println("public %s(%s configuration, io.reactiverse.pgclient.PgClient delegate, %s vertx) {", className, Configuration.class, base.renderFQVertxName());
                                out.tab(2).println("super(%s, %s.class, new %s(configuration,delegate,%s,vertx));", tableIdentifier, pType, base.renderQueryExecutor(tableRecord, pType, tType), mapperFactory);
                                out.tab(1).println("}");
                            })
                    );
                case RX:
                    return new DIStepImpl(base
                            .setWriteDAOImportsDelegate(base.writeDAOImportsDelegate.andThen(out -> out.println("import io.github.jklingsporn.vertx.jooq.rx.reactivepg.ReactiveRXQueryExecutor;")))
                            .setRenderQueryExecutorDelegate((rType, pType, tType) -> String.format("ReactiveRXQueryExecutor<%s,%s,%s>", rType, pType, tType))
                            .setWriteConstructorDelegate((out, className, tableIdentifier, tableRecord, pType, tType) -> {
                                //pType = foo.bar.pojos.Somepojo -> split[0]=foo.bar. -> split[1]=Somepojo
                                String[] split = pType.split("pojos.");
                                String mapperFactory = String.format("%smappers.RowMappers.get%sMapper()",split[0],split[1]);
                                out.tab(1).javadoc("@param configuration The Configuration used for rendering and query execution.\n" +
                                        "     * @param vertx the vertx instance");
                                out.tab(1).println("public %s(%s configuration, io.reactiverse.reactivex.pgclient.PgClient delegate) {", className, Configuration.class);
                                out.tab(2).println("super(%s, %s.class, new %s(configuration,delegate,%s));", tableIdentifier, pType, base.renderQueryExecutor(tableRecord, pType, tType), mapperFactory);
                                out.tab(1).println("}");
                            })
                    );
                default: throw new UnsupportedOperationException(base.apiType.toString());
            }
        }
    }

    static class DIStepImpl extends FinalStepImpl implements DIStep{

        public DIStepImpl(ComponentBasedVertxGenerator base) {
            super(base);
        }

        @Override
        public FinalStep withGuice(boolean generateGuiceModules) {
            base.setWriteDAOConstructorAnnotationDelegate((out)->out.tab(1).println("@javax.inject.Inject"));
            base.setWriteDAOClassAnnotationDelegate((out)-> out.println("@javax.inject.Singleton"));
            if (generateGuiceModules) {
                base.addWriteExtraDataDelegate((schema, writerGen) -> {
                    ComponentBasedVertxGenerator.logger.info("Generate DaoModule ... ");
                    String daoClassName;
                    switch (base.apiType) {
                        case CLASSIC:
                            daoClassName = "io.github.jklingsporn.vertx.jooq.classic.VertxDAO";
                            break;
                        case COMPLETABLE_FUTURE:
                            daoClassName = "io.github.jklingsporn.vertx.jooq.completablefuture.VertxDAO";
                            break;
                        case RX:
                            daoClassName = "io.github.jklingsporn.vertx.jooq.rx.VertxDAO";
                            break;
                        default:
                            throw new UnsupportedOperationException(base.apiType.toString());
                    }
                    String packageName = (base.getActiveGenerator().getStrategy().getTargetDirectory() + "/" + base.getActiveGenerator().getStrategy().getJavaPackageName(schema) + ".tables.modules").replaceAll("\\.", "/");
                    File moduleFile = new File(packageName, "DaoModule.java");
                    JavaWriter out = writerGen.apply(moduleFile);
                    out.println("package " + base.getActiveGenerator().getStrategy().getJavaPackageName(schema) + ".tables.modules;");
                    out.println();
                    out.println("import com.google.inject.AbstractModule;");
                    out.println("import com.google.inject.TypeLiteral;");
                    out.println("import %s;", daoClassName);
                    out.println();
                    out.println("public class DaoModule extends AbstractModule {");
                    out.tab(1).println("@Override");
                    out.tab(1).println("protected void configure() {");
                    for (TableDefinition table : schema.getTables()) {
                        UniqueKeyDefinition key = table.getPrimaryKey();
                        if (key == null) {
                            ComponentBasedVertxGenerator.logger.info("{} has no primary key. Skipping...", out.file().getName());
                            continue;
                        }
                        final String keyType = base.getActiveGenerator().getKeyType(key);
                        final String tableRecord = base.getActiveGenerator().getStrategy().getFullJavaClassName(table, GeneratorStrategy.Mode.RECORD);
                        final String pType = base.getActiveGenerator().getStrategy().getFullJavaClassName(table, GeneratorStrategy.Mode.POJO);
                        final String className = base.getActiveGenerator().getStrategy().getFullJavaClassName(table, GeneratorStrategy.Mode.DAO);
                        if (base.generateInterfaces()) {
                            String iType = base.getActiveGenerator().getStrategy().getFullJavaClassName(table, GeneratorStrategy.Mode.INTERFACE);
                            out.tab(2).println("bind(new TypeLiteral<VertxDAO<%s, ? extends %s, %s>>() {}).to(%s.class).asEagerSingleton();",
                                    tableRecord, iType, keyType, className);
                        }
                        out.tab(2).println("bind(new TypeLiteral<VertxDAO<%s, %s, %s>>() {}).to(%s.class).asEagerSingleton();",
                                tableRecord, pType, keyType, className);
                    }
                    out.tab(1).println("}");
                    out.println("}");
                    return out;
                });
            }
            return new FinalStepImpl(base);
        }


    }

    static class FinalStepImpl implements FinalStep{

        protected final ComponentBasedVertxGenerator base;

        FinalStepImpl(ComponentBasedVertxGenerator base) {
            this.base = base;
        }

        @Override
        public ComponentBasedVertxGenerator build() {
            return base;
        }


    }

}
