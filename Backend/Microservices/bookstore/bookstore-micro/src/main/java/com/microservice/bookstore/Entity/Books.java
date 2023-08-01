package com.microservice.bookstore.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import java.sql.Blob;

@Entity
@Table
public class Books {
@Id
@GeneratedValue(strategy= GenerationType.IDENTITY)
private int bookId;

@Column
@NotBlank(message = "Author cannot be blank")
private String author;

@Column
private int price;

@Column
@NotBlank(message = " Book Name cannot be blank")
private String bookName;

@Column
@NotBlank(message = "Category cannot be blank")
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
