package com.microservice.userinfo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.userinfo.Entity.Users;
import com.microservice.userinfo.Exceptions.UserAlreadyExistsException;
import com.microservice.userinfo.Repository.UsersRepository;
import com.microservice.userinfo.Service.UsersService;

@SpringBootTest
class UserinfoApplicationTests {
	
	@Mock
    private UsersRepository usersRepo;
	
	@Mock
    private RestTemplate restTemplate;
	
	@Mock
	private ObjectMapper objectMapper;

    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private UsersService userService;
   
	
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


	@Test
	void testgetAllUsers() {
		List<Users> users = new ArrayList<>();
		users.add(new Users(1,"Chandrama","chandrama@gmail.com","password@123","admin"));
		users.add(new Users(2,"Ananya","ananya@gmail.com","ananya@123","user"));
		
		when(usersRepo.findAll()).thenReturn(users);
		assertEquals(2, userService.getAllUsers().size());

	}
	
	@Test
	void testaddUsers() throws UserAlreadyExistsException {
		Users dummyUser =new Users(1,"Chandrama","chandrama@gmail.com","password@123","admin");

        when(encoder.encode(dummyUser.getPassword())).thenReturn("password@123");

        
        userService.addUsers(dummyUser);
        verify(usersRepo, times(1)).save(dummyUser);
        verify(encoder, times(1)).encode(dummyUser.getPassword());
    }
	
	
	

}
		
	