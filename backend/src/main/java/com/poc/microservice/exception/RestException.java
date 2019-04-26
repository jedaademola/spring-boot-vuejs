package com.poc.microservice.exception;


public class RestException extends HBEMicroServiceException {

    private static final long serialVersionUID = 1L;

    public RestException(String errorCode, String message) {
        super(errorCode, message);
    }
}
