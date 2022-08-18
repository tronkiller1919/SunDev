package com.returnorder.managment.gatewayservice.apigatewayservice.exception;

import io.jsonwebtoken.JwtException;

public class CustomExceptionResponse extends RuntimeException {

    public CustomExceptionResponse(String message) {
        super(message);
    }
}
