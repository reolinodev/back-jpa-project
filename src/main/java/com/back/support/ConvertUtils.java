package com.back.support;

import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.core.types.dsl.StringTemplate;
import java.time.LocalDateTime;

public class ConvertUtils {

    public static StringTemplate getParseLocalDateTimeToString(DateTimePath<LocalDateTime> params) {
        return Expressions.stringTemplate("to_char({0}, {1})", params, "yyyy-mm-dd HH:mm:ss");
    }

    public static StringTemplate getParseCodeNm(String codeGrpValue,StringPath params) {
        return Expressions.stringTemplate("sample.fn_get_code_nm({0}, {1})", codeGrpValue, params);
    }

}
