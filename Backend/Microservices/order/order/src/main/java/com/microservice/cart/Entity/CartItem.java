package com.microservice.cart.Entity;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="cartitem")
public class CartItem {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int cartItemId;
	
	@Column
	private int bookId;
	
	@Column
	private int userId;
	
	@Column
	private int price;
	@Column
	private int quantity;
	
	
	
	public CartItem() {
		
	}
	
	public CartItem(int cartItemId, int bookId, int userId, int price,int quantity) {
		super();
		this.cartItemId = cartItemId;
		this.bookId = bookId;
		this.userId = userId;
		this.price = price;
		this.quantity=quantity;
	}


//	public Order(int orderId, int bookId, int userId) {
//		super();
//		this.orderId = orderId;
//		this.bookId = bookId;
//		this.userId = userId;
//	}

	public int getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(int cartItemId) {
		this.cartItemId = cartItemId;
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
	public void setQuantity(int quantity) {
		this.quantity=quantity;
	}
	public int getQuantity() {
		return quantity;
	}
	
	
	
	
	
	
	

}
