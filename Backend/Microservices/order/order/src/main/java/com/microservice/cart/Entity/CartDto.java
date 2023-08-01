package com.microservice.cart.Entity;

public class CartDto {
	
	private int quantity;
	private int bookId;
	
	
	
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public CartDto(int quantity, int bookId) {
		super();
		this.quantity = quantity;
		this.bookId = bookId;
	}

	public CartDto() {
		
	}


	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

}
