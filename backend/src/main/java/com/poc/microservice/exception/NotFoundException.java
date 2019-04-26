package com.poc.microservice.exception;

public class NotFoundException extends HBEMicroServiceException {
    private static final long serialVersionUID = 1L;

    public NotFoundException(String errorCode, String message) {
        super(errorCode, message);
    }
}
