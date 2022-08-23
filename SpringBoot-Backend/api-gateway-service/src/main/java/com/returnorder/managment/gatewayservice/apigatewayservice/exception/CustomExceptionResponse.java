package com.returnorder.managment.gatewayservice.apigatewayservice.exception;

public class CustomExceptionResponse extends RuntimeException {

    /**
	 * 
	 */
	public static long id;
	private static final long serialVersionUID = id;

	public CustomExceptionResponse(String message) {
        super(message);
    }
}
