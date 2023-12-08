package com.capgemini.healthcaresystem.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	
	public ResponseEntity<ErrorInfo> handleIdNotFound(IdNotFoundException idNotFoundException)
	{
		
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorMessage(idNotFoundException.getMsg());
		errorInfo.setHttpStatus(HttpStatus.NOT_FOUND.toString());
		errorInfo.setLocalDateTime(LocalDateTime.now());
		
		return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.NOT_FOUND);
	}
	
}
