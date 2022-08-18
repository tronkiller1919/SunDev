package com.returnorder.managment.authorizationservice.exception;

import com.returnorder.managment.authorizationservice.model.ExceptionResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class CustomResponseEntityException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public final ResponseEntity<ExceptionResponse> badCredentials(BadCredentialsException e, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), e.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> usernameNotFound(UsernameNotFoundException e, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),e.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserDataAlreadyExistsException.class)
    public final ResponseEntity<ExceptionResponse> UserDataAlreadyExists(UserDataAlreadyExistsException e,WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),e.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.METHOD_NOT_ALLOWED);
    }
}
