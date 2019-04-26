package com.poc.microservice.exception;

public class BadCredentialsException extends HBEMicroServiceException {
    private static final long serialVersionUID = 1L;

    public BadCredentialsException(String errorCode, String message) {
        super(errorCode, message);
    }
}
