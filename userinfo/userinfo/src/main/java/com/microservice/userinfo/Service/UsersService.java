package com.microservice.userinfo.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.microservice.userinfo.Entity.Users;
import com.microservice.userinfo.Exceptions.UserAlreadyExistsException;
import com.microservice.userinfo.Exceptions.UserNotFoundException;
import com.microservice.userinfo.Repository.UsersRepository;

@Service
public class UsersService {

	@Autowired
	public UsersRepository usersRepo;
	  
@Autowired
	public PasswordEncoder encoder;
	
	public List<Users> getAllUsers(){
		return usersRepo.findAll();
	}

	public Users addUsers(Users user) throws UserAlreadyExistsException {
		// TODO Auto-generated method stub
		if(this.usersRepo.findById(user.getUserId()).isPresent()||(this.usersRepo.existsByName(user.getName())) ){
			throw new UserAlreadyExistsException();
		}
		else {
		
			user.setPassword(encoder.encode(user.getPassword()));
		    return usersRepo.save(user);
		}
	}

  public Users getByName(String username) {
		return usersRepo.findByName(username);
	}
	
	public void deleteUsers(int userId) throws UserNotFoundException {
		// TODO Auto-generated method stub
		if(this.usersRepo.findById(userId).isPresent()) {
		usersRepo.deleteById(userId);
		}
		else {
			throw new UserNotFoundException();
		}
	}
		
	public boolean ExistsByUserName(String userName) {
		boolean u=usersRepo.existsByName(userName);
		return u;
	}
	
	
}


