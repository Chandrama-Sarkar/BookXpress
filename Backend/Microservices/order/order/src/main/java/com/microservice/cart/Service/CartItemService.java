package com.microservice.cart.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservice.cart.Entity.Books;
import com.microservice.cart.Entity.CartDto;
import com.microservice.cart.Entity.CartInput;
import com.microservice.cart.Entity.CartInputResponseDTO;
import com.microservice.cart.Entity.CartItem;
import com.microservice.cart.Exceptions.BookAlreadyAddedException;
import com.microservice.cart.Exceptions.BookNotFoundException;
import com.microservice.cart.Exceptions.CartEmptyException;
import com.microservice.cart.Exceptions.InitialQuantityCriteriaNotMatchedException;
import com.microservice.cart.Exceptions.UserNotFoundException;
import com.microservice.cart.Repository.CartInputResponseRepository;
import com.microservice.cart.Repository.CartItemRepository;

@Service
public class CartItemService {
	
	@Autowired
	private CartItemRepository cartRepo;
	
	@Autowired
	private CartInputResponseRepository cartinputRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	

	public CartInputResponseDTO addCartItem(CartInput cartInput,String token)throws BookAlreadyAddedException{

		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", token);
		
		HttpEntity<Void> requestEntity= new HttpEntity<>(headers);
		ResponseEntity<String> res= restTemplate.exchange("http://USER-SERVICE/validateToken", HttpMethod.GET, requestEntity, String.class);
		
		String respo= res.getBody().toString();
		System.out.println(respo);
		
	   if(respo.equals("valid")) {
		 CartInputResponseDTO c=new CartInputResponseDTO();
		 ResponseEntity<Integer> price=restTemplate.exchange("http://BOOK-SERVICE:8091/books/"+cartInput.getBookId(),HttpMethod.GET,requestEntity,Integer.class);
	     Integer actualPrice=price.getBody();
	     ResponseEntity<Books> book=restTemplate.exchange("http://BOOK-SERVICE:8091/books/getbookbyid/"+cartInput.getBookId(),HttpMethod.GET,requestEntity,Books.class); 
	     
	     CartInputResponseDTO check=cartinputRepo.getCartItemByUserIdAndBookId(cartInput.getBookId(),cartInput.getUserId());
	     if(check != null){	
	    	 throw new BookAlreadyAddedException(); 
			 }
	     else {
	     c.setBookId(cartInput.getBookId());
	     c.setUserId(cartInput.getUserId());
	     c.setQuantity(cartInput.getQuantity());
	     c.setPrice((actualPrice*cartInput.getQuantity()));
	     c.setBookName(book.getBody().getBookName());
	     c.setAuthor(book.getBody().getAuthor());
	     c.setCategory(book.getBody().getCategory());
	      return cartinputRepo.save(c);
		 }
		 }
	   else {
		   System.out.println("Token invalid");
			return null;
	   }
}

	
	
	public List<CartInputResponseDTO> getCartItemsOfUser(Integer userId,String token)throws CartEmptyException{

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", token);
		
		HttpEntity<Void> requestEntity= new HttpEntity<>(headers);
		ResponseEntity<String> res= restTemplate.exchange("http://USER-SERVICE/validateToken", HttpMethod.GET, requestEntity, String.class);
		
		String respo= res.getBody().toString();
		System.out.println(respo);
		
	   if(respo.equals("valid")) {
		   if(cartinputRepo.getCartItemByUserId(userId)!=null)
		   	return cartinputRepo.getCartItemByUserId(userId);
		   else
			   throw new CartEmptyException();
	   }
	   else {
		   System.out.println("Token invalid");
			return null;
	   }
}
	
	public void deleteCartItem(int cartresponseId,String token){
		 
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", token);
		
		HttpEntity<Void> requestEntity= new HttpEntity<>(headers);
		ResponseEntity<String> res= restTemplate.exchange("http://USER-SERVICE/validateToken", HttpMethod.GET, requestEntity, String.class);
		
		String respo= res.getBody().toString();
		System.out.println(respo);
		
		if(respo.equals("valid")) {	
	if(this.cartinputRepo.findById(cartresponseId).isPresent()){
		// TODO Auto-generated method stub
		cartinputRepo.deleteById(cartresponseId);
		}
   }
	
		else {
			System.out.println("Token invalid");
	
		}
}	
	public int getTotalAmount(int uid,String token){
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", token);
		
		HttpEntity<Void> requestEntity= new HttpEntity<>(headers);
		ResponseEntity<String> res= restTemplate.exchange("http://USER-SERVICE/validateToken", HttpMethod.GET, requestEntity, String.class);
		
		String respo= res.getBody().toString();
		System.out.println(respo);
		
	   if(respo.equals("valid")) {
		return cartinputRepo.getTotalPrice(uid);
	   
	   }
	   else {
		   System.out.println("Token invalid");
			return -1;
	   }
	}
}
	
	
