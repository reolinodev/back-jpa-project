package com.back.domain.common;

public final class ExceptSecurityConstants {

    /**
     * Resource 대상
     */
    public static final String[] resourceArray = new String[] {
//        "/page/**",
//        "/dist/**",
//        "/lib/**",
//        "/favicon.ico"
    };

    /**
     * 인터셉터 제외 URL
     */
    public static final String[] permitAllArray = new String[] {
        "/",
        "/api/certification",
        "/api/item/**/**",
        "/api/item/**/**/**",
    };
}
