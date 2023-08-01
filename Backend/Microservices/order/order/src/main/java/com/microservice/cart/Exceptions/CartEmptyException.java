package com.microservice.cart.Exceptions;

public class CartEmptyException extends Exception{
private String message;
	
	public CartEmptyException() {}
	
	public CartEmptyException(String message) {
		super(message);
		this.message=message;
}
}
