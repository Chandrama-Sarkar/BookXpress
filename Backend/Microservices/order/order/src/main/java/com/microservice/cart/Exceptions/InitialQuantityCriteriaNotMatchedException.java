package com.microservice.cart.Exceptions;

public class InitialQuantityCriteriaNotMatchedException extends Exception{
private String message;
	
	public InitialQuantityCriteriaNotMatchedException() {}
	
	public InitialQuantityCriteriaNotMatchedException(String message) {
		super(message);
		this.message=message;
}
}
