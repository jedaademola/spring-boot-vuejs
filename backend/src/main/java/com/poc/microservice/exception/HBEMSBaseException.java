package com.poc.microservice.exception;

public class HBEMSBaseException extends Exception {
	private static final long serialVersionUID = 1L;

	public HBEMSBaseException() {
		super();
	}

	public HBEMSBaseException(String message, Exception e) {
		super(message, e);
	}

	public HBEMSBaseException(String message) {
		super(message);
	}

	public HBEMSBaseException(Throwable e) {
		super(e);
	}
}
