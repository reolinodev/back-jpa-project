package com.back.domain.common;

import lombok.Data;

@Data
public class ErrorMessage {

    public String field;

    public String message;

    public String invalidValue;
}
