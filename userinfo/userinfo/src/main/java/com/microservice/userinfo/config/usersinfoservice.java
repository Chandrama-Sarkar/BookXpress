package com.microservice.userinfo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.microservice.userinfo.Entity.Users;
import com.microservice.userinfo.Repository.UsersRepository;

@Component
public class usersinfoservice implements UserDetailsService{
	
	@Autowired
	private UsersRepository uRepo;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		Users user= uRepo.findByName(userName); 
		System.out.println("From usersinfoservice"+ user.getName());
		return new usersinfo(user);
	}

}
