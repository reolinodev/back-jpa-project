package com.back.domain.common;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorResponse {

    public String requestUrl;

    public String message;

    public String resultCode;

    public List<ErrorMessage> errorList;
}
