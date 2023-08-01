package com.microservice.cart.Controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.cart.Entity.CartDto;
import com.microservice.cart.Entity.CartInput;
import com.microservice.cart.Entity.CartInputResponseDTO;
import com.microservice.cart.Entity.CartItem;
import com.microservice.cart.Exceptions.BookAlreadyAddedException;
import com.microservice.cart.Exceptions.BookNotFoundException;
import com.microservice.cart.Exceptions.CartEmptyException;
import com.microservice.cart.Exceptions.InitialQuantityCriteriaNotMatchedException;
import com.microservice.cart.Exceptions.UserNotFoundException;
import com.microservice.cart.Service.CartItemService;


@RestController
@CrossOrigin("*")
public class CartItemController {

	@Autowired
	private CartItemService cartItemService;
	Logger logger=LoggerFactory.getLogger(CartItemService.class);
	
	@PostMapping("/cartitems/add")
	public CartInputResponseDTO addCartItem(@RequestBody CartInput cartInput,@RequestHeader(name=HttpHeaders.AUTHORIZATION) String token)throws BookAlreadyAddedException{
		logger.info("Item added to cart successfully!");
		return cartItemService.addCartItem(cartInput,token);
	}
	
	@GetMapping("/cartitems/getallItemsofuser/{uid}")
	public List<CartInputResponseDTO> getCartItemsOfUser(@PathVariable Integer uid,@RequestHeader(name=HttpHeaders.AUTHORIZATION) String token)throws CartEmptyException{
		logger.info("Cart items successfully retrieved!!");
		return cartItemService.getCartItemsOfUser(uid,token);
	}
	
	@RequestMapping(method = RequestMethod.DELETE,value="/cartitems/{cartresponseId}")
	@ResponseBody
	public String deleteCartItem(@PathVariable int cartresponseId,@RequestHeader(name=HttpHeaders.AUTHORIZATION) String token)throws BookNotFoundException {
		cartItemService.deleteCartItem(cartresponseId,token);
		logger.info("Book deleted successfully");
		return "Deletion successful!";
	}
	
	
	  
	@GetMapping("/cartitems/gettotalamount/{uid}")
	public int getTotalAmount(@PathVariable Integer uid,@RequestHeader(name=HttpHeaders.AUTHORIZATION) String token ) 
	{
		logger.info("Amount fetched successfully!");
		return cartItemService.getTotalAmount(uid,token);
	    
	}
	

}
