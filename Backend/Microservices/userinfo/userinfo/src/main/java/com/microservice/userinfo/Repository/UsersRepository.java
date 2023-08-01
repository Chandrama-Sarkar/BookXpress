package com.microservice.userinfo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.microservice.userinfo.Entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users,Integer> {

	boolean existsByName(String userName);
	
	Users findByName(String name);
	
	

}