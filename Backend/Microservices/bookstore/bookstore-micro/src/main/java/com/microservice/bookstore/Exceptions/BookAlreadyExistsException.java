package com.microservice.bookstore.Exceptions;

public class BookAlreadyExistsException extends Exception{
	private String message;
	public BookAlreadyExistsException() {}
   public BookAlreadyExistsException(String message) {
	super(message);
	this.message=message;
}
}