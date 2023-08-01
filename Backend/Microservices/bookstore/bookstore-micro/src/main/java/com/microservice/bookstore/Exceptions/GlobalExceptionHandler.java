package com.microservice.bookstore.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value=BookNotFoundException.class)
	public ResponseEntity bookNotFound(BookNotFoundException ex){
	return new ResponseEntity("Book does not exists",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=BookAlreadyExistsException.class)
	public ResponseEntity method2(BookAlreadyExistsException ex){
	 return new ResponseEntity("Book already exists",HttpStatus.CONFLICT);
	}

	
}
