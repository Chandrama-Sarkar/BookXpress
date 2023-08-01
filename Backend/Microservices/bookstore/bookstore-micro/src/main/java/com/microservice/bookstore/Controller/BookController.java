package com.microservice.bookstore.Controller;

import org.springframework.http.HttpHeaders;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.bookstore.Entity.Books;
import com.microservice.bookstore.Exceptions.BookAlreadyExistsException;
import com.microservice.bookstore.Exceptions.BookNotFoundException;
import com.microservice.bookstore.Service.BookService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestHeader;
@RestController
@CrossOrigin
public class BookController {
	
	@Autowired
	private BookService bookService;
	Logger logger=LoggerFactory.getLogger(BookService.class);

	@PostMapping("/books/add")
	public Books addBooks(@Valid @RequestBody Books book,@RequestHeader(name=HttpHeaders.AUTHORIZATION) String token) throws BookAlreadyExistsException{
		logger.info("Book added!");
		return bookService.addBooks(book,token);
	}
	
	@GetMapping("/books/all")
	public List<Books> getAllBooks(@RequestHeader(name=HttpHeaders.AUTHORIZATION) String token){
		logger.info("Book details fetched Successfully!");
		System.out.println("FROM BOOK CONTROLLER "+token);
		return bookService.getAllBooks(token);
	}
	
	
	@GetMapping("books/getbookbyid/{bookId}")
	public Optional<Books> getBookById(@PathVariable Integer bookId,@RequestHeader(name=HttpHeaders.AUTHORIZATION) String token)
	{
		return bookService.getBookById(bookId,token);
	}
	
	@PutMapping("/books/{bookId}")
	public Books updateBooks(@PathVariable Integer bookId,@RequestBody Books book,@RequestHeader(name=HttpHeaders.AUTHORIZATION) String token){
		logger.info("Book detail updated successfully!");
		return bookService.updateBooks(bookId,book,token);
	}

	@RequestMapping(method = RequestMethod.DELETE,value="/books/{bookId}")
	@ResponseBody
	public String deleteBooks(@PathVariable int bookId,@RequestHeader(name=HttpHeaders.AUTHORIZATION) String token)throws BookNotFoundException {
		bookService.deleteBooks(bookId,token);
		logger.info("Book deleted successfully");
		return "Deletion successful!";
	}
	
	@GetMapping("/books/{bookId}")
	public int getPriceOfBook(@PathVariable Integer bookId,@RequestHeader(name=HttpHeaders.AUTHORIZATION) String token)throws BookNotFoundException {
		logger.info("Price of book fetched successfully");
		return bookService.getPriceOfBook(bookId,token);
	}

	
	
}
