package com.app.boot.exceptionHandler;

public class MissingHeaderArgException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MissingHeaderArgException(String msg) {
		super(msg);
	}
}
