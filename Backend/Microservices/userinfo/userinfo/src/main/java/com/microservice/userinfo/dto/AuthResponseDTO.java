package com.microservice.userinfo.dto;

import com.microservice.userinfo.Entity.Users;

public class AuthResponseDTO {
	
	private String token;
	private Users user;
	
	public AuthResponseDTO() {
		
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Users getuser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public AuthResponseDTO(String token, Users user) {
		super();
		this.token = token;
		this.user = user;
	}
	
	

}
