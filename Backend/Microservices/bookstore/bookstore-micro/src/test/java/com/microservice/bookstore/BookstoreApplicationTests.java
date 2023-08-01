package com.microservice.bookstore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.bookstore.Entity.Books;
import com.microservice.bookstore.Exceptions.BookAlreadyExistsException;
import com.microservice.bookstore.Repository.BookRepository;
import com.microservice.bookstore.Service.BookService;

@SpringBootTest
class BookstoreApplicationTests {

	@Mock
	BookRepository booksRepo;
	
	@InjectMocks
	BookService bookService;
	
	@Mock
    private RestTemplate restTemplate;
	
	@Mock
    private ObjectMapper objectMapper;
	
    @BeforeEach
	public void setUp() {
	 MockitoAnnotations.openMocks(this);
	    }
	@Test
	void contextLoads() {
	}
	
	@Test
    public void testgetAllBooks(){
   
        ResponseEntity<String> responseEntity = new ResponseEntity<>("valid", HttpStatus.OK);
        when(restTemplate.exchange(
                eq("http://USER-SERVICE:8092/validateToken"),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(String.class)
        )).thenReturn(responseEntity);
        
        List<Books> expectedBooks = Arrays.asList(new Books(), new Books());
        when(booksRepo.findAll()).thenReturn(expectedBooks);
        
        List<Books> actualBooks = bookService.getAllBooks("valid-token");
       
        assertEquals(expectedBooks, actualBooks);
        verify(restTemplate, times(1)).exchange(
                eq("http://USER-SERVICE:8092/validateToken"),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(String.class)
        );
        verify(booksRepo, times(1)).findAll();
    }
	
	@Test
	 void testaddBooks() throws BookAlreadyExistsException {

	        String token = "validdummytoken";
	        Books book = new Books();
	        book.setBookId(1);
	        book.setBookName("Bookname");
	        book.setAuthor("Author");
	        book.setCategory("category");
	        book.setPrice(500);

	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        headers.set("Authorization", token);
	        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

	        ResponseEntity<String> responseEntity = ResponseEntity.ok("valid");
	        when(restTemplate.exchange(eq("http://USER-SERVICE/validateToken"), eq(HttpMethod.GET), eq(requestEntity), eq(String.class)))
	                .thenReturn(responseEntity);

	        when(booksRepo.findById(eq(book.getBookId()))).thenReturn(java.util.Optional.empty());
	        when(booksRepo.existsByBookName(eq(book.getBookName()))).thenReturn(false);
	        when(booksRepo.save(eq(book))).thenReturn(book);

	        Books result = bookService.addBooks(book, token);

	        assertEquals(book, result);
	    }
	
	
}
