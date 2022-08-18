package com.returnorder.managment.componentprocessingservice.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.io.InputStream;

public class ExceptionMessageDecoder implements ErrorDecoder {

    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        ExceptionResponse exceptionResponse;
        try(InputStream responseBody = response.body().asInputStream()){
            ObjectMapper mapper = new ObjectMapper();
            exceptionResponse = mapper.readValue(responseBody, ExceptionResponse.class);

        }catch(IOException ex){
            return new Exception(ex.getMessage());
        }

        return switch (response.status()) {
            case 400 -> new FeignClientException(exceptionResponse.getMessage() != null ? exceptionResponse.getMessage() : "Bad Request");
            case 404 -> new FeignClientException(exceptionResponse.getMessage() != null ? exceptionResponse.getMessage() : "Not found");
            default -> errorDecoder.decode(methodKey, response);
        };
    }
}
