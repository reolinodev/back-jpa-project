package com.back.domain.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Header {

    public String requestUrl;

    public String method;

    public String message;

    public String resultCode;
}
