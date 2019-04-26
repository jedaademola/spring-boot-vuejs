package com.poc.microservice.exception;

import java.util.List;

public class BadRequestException extends HBEMicroServiceException {
    private static final long serialVersionUID = 1L;

    public BadRequestException(String errorCode, String message, List<Error> errors) {
        super(errorCode, message, errors);
    }
    
    public BadRequestException(String errorCode, String message) {
        super(errorCode, message);
    }
}