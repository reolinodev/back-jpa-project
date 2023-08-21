package com.back.config;

import org.hibernate.dialect.PostgreSQL10Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StringType;

public class PostgreSQLConfig extends PostgreSQL10Dialect {

    public PostgreSQLConfig() {
        super();
        this.registerFunction("ws.fn_get_code_nm", new StandardSQLFunction("ws.fn_get_code_nm", new StringType()));
        this.registerFunction("ws.fn_get_user_nm", new StandardSQLFunction("ws.fn_get_user_nm", new StringType()));
        this.registerFunction("ws.fn_get_menu_nm", new StandardSQLFunction("ws.fn_get_menu_nm", new StringType()));
    }
}
