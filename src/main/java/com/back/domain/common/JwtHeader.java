package com.back.domain.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class JwtHeader {

    public String requestUrl;

    public String message;

    public String method;

    public String resultCode;

    public String accessToken;

    public String refreshToken;

}
