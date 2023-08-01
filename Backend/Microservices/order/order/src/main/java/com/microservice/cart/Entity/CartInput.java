package com.microservice.cart.Entity;

import jakarta.persistence.Column;

public class CartInput {
	
	private int bookId;
	
	private int userId;
	private int quantity;
	
	public CartInput() {
		
	}

	public CartInput(int bookId, int userId,int quantity) {
		super();
		this.bookId = bookId;
		this.userId = userId;
		this.quantity=quantity;
	}

	

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}
