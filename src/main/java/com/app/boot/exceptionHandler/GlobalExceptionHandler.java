package com.app.boot.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler({ UserNotFoundException.class })
	public ResponseEntity<ErrorDetails> userNotFound(Exception exception) {
		ErrorDetails errorDetails = new ErrorDetails("1080", exception.getMessage());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.OK);
	}

	@ExceptionHandler({ MissingHeaderArgException.class })
	public ResponseEntity<ErrorDetails> missingHeader(Exception exception) {
		ErrorDetails errorDetails = new ErrorDetails("3030", exception.getMessage());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.OK);
	}

	@ExceptionHandler({ InvalidHeaderValueException.class })
	public ResponseEntity<ErrorDetails> invalidHeaderValue(Exception exception) {
		ErrorDetails errorDetails = new ErrorDetails("2020", exception.getMessage());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.OK);
	}
}
