package com.hbe.ms.common.exception;

import java.util.List;

import com.hbe.ms.common.vo.Error;

public class BadRequestException extends HBEMicroServiceException {
    private static final long serialVersionUID = 1L;

    public BadRequestException(String errorCode, String message, List<Error> errors) {
        super(errorCode, message, errors);
    }
    
    public BadRequestException(String errorCode, String message) {
        super(errorCode, message);
    }
}