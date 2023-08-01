package com.microservice.userinfo.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name="Users")
public class Users {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int userId;
	
	@Column
	@NotBlank(message = "Username is required")
	private String name;
	
	@Column
	@NotBlank(message = "Email cannot be blank")
	@Email
	private String email;
	
	@Column
	@NotBlank(message = "Password is required")
	private String password;
	
	@Column
	@NotBlank(message = "Role Cannot be blank")
	private String role;
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Users() {

	}
	public Users(int userId, String name, String email, String password,String role) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role=role;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
