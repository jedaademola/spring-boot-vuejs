package com.hbe.ms.common.exception;

import java.util.List;

import com.hbe.ms.common.vo.Error;

public class HBEMicroServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;


	private String errorCode = null;
    private List<Error> errors;

	public HBEMicroServiceException() {
		super();
	}

	public HBEMicroServiceException(String message, Exception e) {
		super(message, e);
	}

	public HBEMicroServiceException(String message) {
		super(message);
	}

	public HBEMicroServiceException(Throwable e) {
		super(e);
	}

    public HBEMicroServiceException(String errorCode, String message, List<Error> errors) {
        super(message);
		this.errorCode = errorCode;
        this.errors = errors;
	}

    public HBEMicroServiceException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

    public HBEMicroServiceException(String errorCode, String message, Exception e) {
        super(message, e);
        this.errorCode = errorCode;
    }
    
    

	/**
	 * Returns the errorCode
	 *
	 * @return errorCode String
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * Sets the errorCode
	 *
	 * @param errorCode
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

    /**
     * @return the errors
     */
    public List<Error> getErrors() {
        return errors;
    }

    /**
     * @param errors the errors to set
     */
    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

}