package com.newbuy.in.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.newbuy.in.DTO.ApiResponse;
import com.newbuy.in.model.UserStaging;
import com.newbuy.in.model.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {
	
    Users findByEmail(String email);
    
    @Query("select e from Users e where e.email = ?1 and e.password = ?2")
    Users login(String email , String password);


}
