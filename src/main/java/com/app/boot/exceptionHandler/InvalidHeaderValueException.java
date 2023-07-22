package com.app.boot.exceptionHandler;

public class InvalidHeaderValueException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidHeaderValueException(String msg) {
		super(msg);
	}
}
