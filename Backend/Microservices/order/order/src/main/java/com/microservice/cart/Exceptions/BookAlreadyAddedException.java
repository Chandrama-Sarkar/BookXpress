package com.microservice.cart.Exceptions;

public class BookAlreadyAddedException  extends Exception  {

private String message;
	
	public BookAlreadyAddedException(){}
	
	public BookAlreadyAddedException(String message) {
		super(message);
		this.message=message;
}
}
