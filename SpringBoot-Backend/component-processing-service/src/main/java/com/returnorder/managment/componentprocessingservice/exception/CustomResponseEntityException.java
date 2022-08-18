package com.returnorder.managment.componentprocessingservice.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@RestControllerAdvice
public class CustomResponseEntityException extends ResponseEntityExceptionHandler {

//    Exception to handle when request data is not of type JSON
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errMessage = ex.getMessage()+". Only JSON type supported";
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),errMessage, request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(FeignClientException.class)
    public final ResponseEntity<ExceptionResponse> handleFeignClientException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(CannotPlaceOrderException.class)
    public final ResponseEntity<ExceptionResponse> handleCannotPlaceOrderException(Exception e,WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),e.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String  method = ex.getMethod();
        String message;
        message = method.equals("POST") ? "Expecting GET method instead got "+ex.getMethod(): "Expecting POST method instead got "+ex.getMethod();

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),message, request.getDescription(false));


        return new ResponseEntity<>(exceptionResponse, HttpStatus.METHOD_NOT_ALLOWED);
    }
}
