package com.poc.microservice.exception;


public class ConflictException extends HBEMicroServiceException {

    private static final long serialVersionUID = 1L;

    public ConflictException(String errorCode, String message) {
        super(errorCode, message);
    }
}
