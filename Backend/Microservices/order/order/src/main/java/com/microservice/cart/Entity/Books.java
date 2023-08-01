package com.microservice.cart.Entity;


public class Books {

	private int bookId;
	private String author;
	private int price;
	private String bookName;
    private String category;

	public Books() {
		super();
	}
	public Books(int bookId, String author,String bookName,String category, int price) {
		super();
		this.bookId = bookId;
		this.author = author;
		this.price = price;
		this.bookName=bookName;
		this.category=category;
		
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}



}
