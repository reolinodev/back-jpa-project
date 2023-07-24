package com.back.support;

import com.back.domain.common.Header;
import com.back.domain.common.JwtHeader;
import javax.servlet.http.HttpServletRequest;

public class ResponseUtils {

    public static Header setHeader(String message, String code,
        HttpServletRequest httpServletRequest) {
        Header header = new Header();
        header.message = message;
        header.method = httpServletRequest.getMethod();
        header.requestUrl = httpServletRequest.getRequestURI();
        header.resultCode = code;
        return header;
    }

    public static JwtHeader setJwtHeader(String message, String code, String accessToken,
        String refreshToken, HttpServletRequest httpServletRequest) {

        JwtHeader jwtHeader = new JwtHeader();
        jwtHeader.message = message;
        jwtHeader.method = httpServletRequest.getMethod();
        jwtHeader.requestUrl = httpServletRequest.getRequestURI();
        jwtHeader.resultCode = code;
        jwtHeader.accessToken = accessToken;
        jwtHeader.refreshToken = refreshToken;
        return jwtHeader;
    }
}
