package com.returnorder.managment.authorizationservice.exception;

public class UserDataAlreadyExistsException extends RuntimeException{
    public UserDataAlreadyExistsException(String message) {
        super(message);
    }
}
