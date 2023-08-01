package com.microservice.userinfo.Controller;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.microservice.userinfo.Entity.Users;
import com.microservice.userinfo.Exceptions.UserAlreadyExistsException;
import com.microservice.userinfo.Exceptions.UserNotFoundException;
import com.microservice.userinfo.Service.UsersService;
import com.microservice.userinfo.dto.AuthRequest;
import com.microservice.userinfo.dto.AuthResponseDTO;
import com.microservice.userinfo.jwtservices.jwtservice;

import jakarta.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@CrossOrigin("*")
@RestController
public class UsersController {
	
	@Autowired
	private UsersService userService;
	
	@Autowired
    private jwtservice jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;
	
	
	Logger logger=LoggerFactory.getLogger(UsersController.class);

	@PostMapping("/users/add")
	public Users addUsers(@Valid @RequestBody Users user)throws UserAlreadyExistsException {
		//return new ResponseEntity(this.userService.addUsers(user),HttpStatus.CREATED);
		logger.info("User added successfully!");
		return userService.addUsers(user);
		
	}
	
	@GetMapping("/users/all")
	@PreAuthorize("hasAuthority('admin')")
	public List<Users> getAllUsers(){
		logger.info("User details fetched successfully!");
		return userService.getAllUsers();
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody AuthRequest authRequest) {
		System.out.println("AuthRequest name :- "+authRequest.getName());
		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getName(), authRequest.getPassword()));
			if(authentication.isAuthenticated()) {
				Users u = userService.getByName(authRequest.getName());
	        	String token= jwtService.generateToken(authRequest.getName());
	        	System.out.println(token);
	        	AuthResponseDTO responseDTO= new AuthResponseDTO();
	        	responseDTO.setToken(token);
	        	responseDTO.setUser(u);
	        	return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
			}catch(Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE,value="/users/{userId}")
	@PreAuthorize("hasAuthority('admin')")
	@ResponseBody
	public void deleteUsers(@PathVariable int userId)throws UserNotFoundException {
		logger.info("User deleted successfully!");
		userService.deleteUsers(userId);
	}
	
	@GetMapping("/validateToken")
	 public String isTokenValid(@RequestHeader(name=HttpHeaders.AUTHORIZATION) String token) {
		System.out.println("FROM USER SERVICE "+token);
		 return jwtService.isTokenValid(token.substring(7));
	 }

	
	
}
