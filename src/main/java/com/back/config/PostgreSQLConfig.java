package com.back.config;

import org.hibernate.dialect.PostgreSQL10Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StringType;

public class PostgreSQLConfig extends PostgreSQL10Dialect {

    public PostgreSQLConfig() {
        super();

        this.registerFunction("test", new StandardSQLFunction("test", new StringType()));

        this.registerFunction("sample.fn_get_code_nm", new StandardSQLFunction("sample.fn_get_code_nm", new StringType()));
    }
}
