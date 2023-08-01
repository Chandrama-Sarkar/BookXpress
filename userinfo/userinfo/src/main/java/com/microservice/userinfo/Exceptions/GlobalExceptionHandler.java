package com.microservice.userinfo.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value=UserNotFoundException.class)
	public ResponseEntity userNotFound(UserNotFoundException ex){
	return new ResponseEntity("User does not exists",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=UserAlreadyExistsException.class)
	public ResponseEntity method2(UserAlreadyExistsException ex){
	 return new ResponseEntity("User already exists",HttpStatus.CONFLICT);
	}

	
}
