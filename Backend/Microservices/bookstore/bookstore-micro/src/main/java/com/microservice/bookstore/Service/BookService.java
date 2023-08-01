package com.microservice.bookstore.Service;

import java.util.ArrayList;
import org.springframework.http.MediaType;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.bookstore.Entity.Books;
import com.microservice.bookstore.Exceptions.BookAlreadyExistsException;
import com.microservice.bookstore.Exceptions.BookNotFoundException;
import com.microservice.bookstore.Repository.BookRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
@Service
public class BookService{
 @Autowired
 public BookRepository booksRepo;
 @Autowired
	private RestTemplate restTemplate;

 public List<Books> getAllBooks(String token){
	 HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", token);
		
		HttpEntity<Void> requestEntity= new HttpEntity<>(headers);
		ResponseEntity<String> res= restTemplate.exchange("http://USER-SERVICE:8092/validateToken", HttpMethod.GET, requestEntity, String.class);
		
		String respo= res.getBody().toString();
		System.out.println(respo);
		if(respo.equals("valid")) {
	 
		return booksRepo.findAll();
		
	}
		else {
			System.out.println("Token invalid");
			return null;
		}
 }
 public Books addBooks(Books book,String token)throws BookAlreadyExistsException {
		// TODO Auto-generated method stub
	 	HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", token);
		
		HttpEntity<Void> requestEntity= new HttpEntity<>(headers);
		ResponseEntity<String> res= restTemplate.exchange("http://USER-SERVICE/validateToken", HttpMethod.GET, requestEntity, String.class);
		
		String respo= res.getBody().toString();
		System.out.println(respo);
		
	if(respo.equals("valid")) {
	 if(this.booksRepo.findById(book.getBookId()).isPresent()||(this.booksRepo.existsByBookName(book.getBookName())))
	 {
		 throw new BookAlreadyExistsException();
	 }
	 else
		 return booksRepo.save(book);
	}
	else {
		System.out.println("Token invalid");
		return null;
	}
 }

 
 public Books updateBooks(int bookId, Books book,String token){
		// TODO Auto-generated method stub
	 HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", token);
		
		HttpEntity<Void> requestEntity= new HttpEntity<>(headers);
		ResponseEntity<String> res= restTemplate.exchange("http://USER-SERVICE/validateToken", HttpMethod.GET, requestEntity, String.class);
		
		String respo= res.getBody().toString();
		System.out.println(respo);
		
	if(respo.equals("valid")) {
		
		Optional<Books> b= booksRepo.findById(bookId);
		Books books= b.get();
		if(b.isPresent()) {
		//books.setBookId(book.getBookId());
		books.setAuthor(book.getAuthor());
		books.setBookName(book.getBookName());
		books.setPrice(book.getPrice());
		books.setCategory(book.getCategory());
		return booksRepo.save(books);
		 }
	}
	else {
		System.out.println("Token invalid");
		return null;
	}
	return book;
	
 }

	public void deleteBooks(int bookId,String token)throws BookNotFoundException{
		 
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", token);
		
		HttpEntity<Void> requestEntity= new HttpEntity<>(headers);
		ResponseEntity<String> res= restTemplate.exchange("http://USER-SERVICE/validateToken", HttpMethod.GET, requestEntity, String.class);
		
		String respo= res.getBody().toString();
		System.out.println(respo);
		
		if(respo.equals("valid")) {	
	if(this.booksRepo.findById(bookId).isPresent()){
		// TODO Auto-generated method stub
		booksRepo.deleteById(bookId);
		}
	else
		throw new BookNotFoundException();
}
	
		else {
			System.out.println("Token invalid");
	
		}
}
	
	public int getPriceOfBook(Integer bookId,String token)throws BookNotFoundException {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", token);
		
		HttpEntity<Void> requestEntity= new HttpEntity<>(headers);
		ResponseEntity<String> res= restTemplate.exchange("http://USER-SERVICE/validateToken", HttpMethod.GET, requestEntity, String.class);
		
		String respo= res.getBody().toString();
		System.out.println(respo);
		
		if(respo.equals("valid")) {	
		if(this.booksRepo.findById(bookId).isPresent()){
		return booksRepo.getPriceOfBook(bookId);
	}
		else
			throw new BookNotFoundException();
		}
		else {
			System.out.println("Token invalid");
			return -1;
		}
			
	}
	
	
	public boolean ExistsByBookName(String bookName) {
		boolean b=booksRepo.existsByBookName(bookName);
		return b;
	}
	
	public Optional<Books> getBookById(Integer bookId ,String token) {
		// TODO Auto-generated method stub
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", token);
		
		HttpEntity<Void> requestEntity= new HttpEntity<>(headers);
		ResponseEntity<String> res= restTemplate.exchange("http://USER-SERVICE/validateToken", HttpMethod.GET, requestEntity, String.class);
		
		String respo= res.getBody().toString();
		System.out.println(respo);
		
		if(respo.equals("valid")) {
		return booksRepo.findById(bookId);
	}
		else {
			System.out.println("Token invalid");
			return null;
		}
	
	}



 
}