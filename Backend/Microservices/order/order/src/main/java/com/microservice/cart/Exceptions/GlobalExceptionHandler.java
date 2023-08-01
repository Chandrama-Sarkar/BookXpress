package com.microservice.cart.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.microservice.cart.Exceptions.BookNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler{

	@ExceptionHandler(value=BookNotFoundException.class)
	public ResponseEntity<?> BookNotFound(BookNotFoundException ex){
	return new ResponseEntity("Book does not exists",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=UserNotFoundException.class)
	public ResponseEntity UserNotFound(BookNotFoundException ex){
	return new ResponseEntity("User does not exists",HttpStatus.NOT_FOUND);
	}
	

	@ExceptionHandler(value=CartEmptyException.class)
	public ResponseEntity CartEmptyException(CartEmptyException ex){
	return new ResponseEntity("The Cart for this user is empty",HttpStatus.CONFLICT);
	}
	@ExceptionHandler(value=InitialQuantityCriteriaNotMatchedException.class)
	public ResponseEntity InitialQuantityCriteriaNotMatched(InitialQuantityCriteriaNotMatchedException ex){
	return new ResponseEntity("Initial quantity cannot be greater than 1",HttpStatus.CONFLICT);
	}
	@ExceptionHandler(value=BookAlreadyAddedException.class)
	public ResponseEntity BookAlreadyAdded(BookAlreadyAddedException ex)
	{
		return new ResponseEntity("This book is already added to cart once",HttpStatus.CONFLICT);
	}
	
}
