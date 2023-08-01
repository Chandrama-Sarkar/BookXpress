package com.microservice.cart.Entity;

public class CartInputDTO {
	
	private int UserId;

	public CartInputDTO() {
		
	}
	
	public CartInputDTO(int userId) {
		super();
		UserId = userId;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}
	
	
	
	

}
