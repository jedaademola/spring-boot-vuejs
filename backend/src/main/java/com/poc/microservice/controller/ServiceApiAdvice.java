package com.poc.microservice.controller;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.poc.microservice.exception.BadCredentialsException;
import com.poc.microservice.exception.BadRequestException;
import com.poc.microservice.exception.ConflictException;
import com.poc.microservice.exception.NotFoundException;
import com.poc.microservice.model.Response;
import com.poc.microservice.model.Error;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice(annotations = RestController.class, basePackages = "com.poc.microservice.controller")
@ResponseBody
public class ServiceApiAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceApiAdvice.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Response handleValidationException(MethodArgumentNotValidException e) {
        Response response = new Response();
        response.setCode("400");
        response.setDescription("Input Validation failed");
        BindingResult result = e.getBindingResult();
        List<FieldError> errorList = result.getFieldErrors();
        List<Error> errors = new ArrayList<>();
        for (FieldError fieldError : errorList) {
            errors.add(new Error(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        response.setErrors(errors);
        return response;
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Response handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        Response response = new Response();
        response.setCode("400");
        response.setDescription(e.getLocalizedMessage());

        if (e.getCause() != null) {
            String message = e.getCause().getMessage();
            if (e.getCause() instanceof JsonMappingException) {
                String[] arr = message.split("\\(");
                if (arr.length > 0) {
                    String temp = arr[0];
                    String[] arr2 = message.split("\\[");
                    if (arr2.length > 1) {
                        message = temp + " (fieldName: [" + arr2[1];
                    } else {
                        message = temp;
                    }
                }
            }

            if (e.getCause() instanceof JsonParseException) {
                String[] arr = message.split("at");
                if (arr.length > 0) {
                    String temp = arr[0];
                    JsonParseException jpe = (JsonParseException) e.getCause();
                    message = temp + " [line: " + jpe.getLocation().getLineNr() + ", column: " + jpe.getLocation().getColumnNr() + "]";
                }
            }
            response.setDescription("test:" + message);
        }
        return response;
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Response handleNotFoundException(BadRequestException e) {
        Response response = new Response();
        response.setCode(e.getErrorCode());
        response.setDescription(e.getMessage());
        return response;
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public Response handleBadCredentialException(BadCredentialsException e) {
        Response response = new Response();
        response.setCode(e.getErrorCode());
        response.setDescription(e.getMessage());
        return response;
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Response handleServletRequestBindingException(ServletRequestBindingException e) {
        Response response = new Response();
        response.setCode("10011");
        response.setDescription("Invalid Credentials");
        return response;
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ResponseBody
    public Response handleSecurityAccessDenied(AccessDeniedException e) {
        Response response = new Response();
        response.setCode("403");
        response.setDescription("Security: Access Denied");
        LOGGER.error("Access violation: Access Denied Exception");
        return response;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Response handleException(Exception e) {
        Response response = new Response();
        response.setCode("001");
        response.setDescription(e.getMessage());
        return response;
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public Response handleNotFoundException(NotFoundException ex) {
        Response response;
        response = new Response();
        response.setCode(ex.getErrorCode());
        response.setDescription(ex.getMessage());
        return response;
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ResponseBody
    public Response handleConflictException(ConflictException e) {
        Response response;
        response = new Response();
        response.setCode(e.getErrorCode());
        response.setDescription(e.getMessage());
        return response;
    }


    @ExceptionHandler(com.fasterxml.jackson.databind.exc.InvalidFormatException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Response handleInvalidFormatException(com.fasterxml.jackson.databind.exc.InvalidFormatException ex) {
        Response response = new Response();
        response.setCode("400");
        response.setDescription("Invalid input " + ex.getValue());
        return response;
    }


}
