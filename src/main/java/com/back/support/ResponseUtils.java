package com.back.support;

import com.back.domain.common.Header;
import javax.servlet.http.HttpServletRequest;

public class ResponseUtils {

    public static Header setHeader(String message, String code,
        HttpServletRequest httpServletRequest) {
        Header header = new Header();
        header.setMessage(message);
        header.setMethod(httpServletRequest.getMethod());
        header.setRequestUrl(httpServletRequest.getRequestURI());
        header.setResultCode(code);
        return header;
    }
}
