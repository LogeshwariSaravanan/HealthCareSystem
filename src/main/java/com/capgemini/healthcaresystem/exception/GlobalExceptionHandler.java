package com.capgemini.healthcaresystem.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ErrorInfo> handleIdNotFound(IdNotFoundException idNotFoundException)
	{
		
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorMessage(idNotFoundException.getMsg());
		errorInfo.setHttpStatus(HttpStatus.NOT_FOUND.toString());
		errorInfo.setLocalDateTime(LocalDateTime.now());
		
		return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(InvalidUserNameException.class)
	public ResponseEntity<ErrorInfo> handleInvalidUserName(InvalidUserNameException invalidUserNameException){
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorMessage(invalidUserNameException.getMsg());
		errorInfo.setHttpStatus(HttpStatus.NOT_FOUND.toString());
		errorInfo.setLocalDateTime(LocalDateTime.now());
		return new ResponseEntity<ErrorInfo>(errorInfo,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(InvalidPasswordException.class)
	public ResponseEntity<ErrorInfo> handleInvalidPassword(InvalidPasswordException invalidPasswordException){
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorMessage(invalidPasswordException.getMsg());
		errorInfo.setHttpStatus(HttpStatus.NOT_FOUND.toString());
		errorInfo.setLocalDateTime(LocalDateTime.now());
		return new ResponseEntity<ErrorInfo>(errorInfo,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidContactNumberException.class)
	public ResponseEntity<ErrorInfo> handleInvalidContactNumber(InvalidContactNumberException invalidContactNumberException){
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorMessage(invalidContactNumberException.getMsg());
		errorInfo.setHttpStatus(HttpStatus.NOT_FOUND.toString());
		errorInfo.setLocalDateTime(LocalDateTime.now());
		return new ResponseEntity<ErrorInfo>(errorInfo,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidEmailIdException.class)
	public ResponseEntity<ErrorInfo> handleInvalidEmailid(InvalidEmailIdException invalidEmailIdException){
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorMessage(invalidEmailIdException.getMsg());
		errorInfo.setHttpStatus(HttpStatus.NOT_FOUND.toString());
		errorInfo.setLocalDateTime(LocalDateTime.now());
		return new ResponseEntity<ErrorInfo>(errorInfo,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorInfo> handleUserNotFound(UserNotFoundException userNotFoundException){
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorMessage(userNotFoundException.getMsg());
		errorInfo.setHttpStatus(HttpStatus.NOT_FOUND.toString());
		errorInfo.setLocalDateTime(LocalDateTime.now());
		return new ResponseEntity<ErrorInfo>(errorInfo,HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidUserException.class)
	public ResponseEntity<ErrorInfo> handleInvalidUser(InvalidUserException invalidUserException){
		ErrorInfo errorInfo=new ErrorInfo();
		errorInfo.setErrorMessage(invalidUserException.getMsg());
		errorInfo.setHttpStatus(HttpStatus.NOT_FOUND.toString());
		errorInfo.setLocalDateTime(LocalDateTime.now());
		return new ResponseEntity<ErrorInfo>(errorInfo,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(IdAlreadyExistException.class)
	public ResponseEntity<ErrorInfo> handleIdAlreadyExist(IdAlreadyExistException idAlreadyExistException){
		ErrorInfo errorInfo=new ErrorInfo();
		errorInfo.setErrorMessage(idAlreadyExistException.getMsg());
		errorInfo.setHttpStatus(HttpStatus.NOT_FOUND.toString());
		errorInfo.setLocalDateTime(LocalDateTime.now());
		return new ResponseEntity<ErrorInfo>(errorInfo,HttpStatus.NOT_FOUND);
	}
	
}

