package com.returnorder.managment.componentprocessingservice.exception;

public class FeignClientException extends RuntimeException {

    public FeignClientException(String message) {
        super(message);
    }
}
