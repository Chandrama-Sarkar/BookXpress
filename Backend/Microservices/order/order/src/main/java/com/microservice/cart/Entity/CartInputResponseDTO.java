package com.microservice.cart.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="cartresponsetable")
public class CartInputResponseDTO {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private  int cartresponseId;
	@Column
	private int bookId;
	
	@Column
	private int userId;
	
	@Column
	private int price;
	@Column
	private int quantity;
	@Column
	private String bookName;
	@Column
	private String author;
	@Column
	private String category;
	
	public CartInputResponseDTO() {
		super();
	}
	
	
	public CartInputResponseDTO(int cartresponseId, int bookId, int userId, int price, int quantity, String bookName,
			String author, String category) {
		super();
		this.cartresponseId = cartresponseId;
		this.bookId = bookId;
		this.userId = userId;
		this.price = price;
		this.quantity = quantity;
		this.bookName = bookName;
		this.author = author;
		this.category = category;
	}

	
	

	public int getCartresponseId() {
		return cartresponseId;
	}


	public void setCartresponseId(int cartresponseId) {
		this.cartresponseId = cartresponseId;
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


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
		
}
