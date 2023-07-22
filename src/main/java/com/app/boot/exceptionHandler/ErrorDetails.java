package com.app.boot.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDetails {
	String statusCode;
	String errorMsg;
	
}
