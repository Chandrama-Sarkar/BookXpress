package com.microservice.userinfo.Exceptions;

public class UserAlreadyExistsException extends Exception{
	private String message;
	public UserAlreadyExistsException() {}
public UserAlreadyExistsException(String message) {
	super(message);
	this.message=message;
}
}

