package com.newbuy.in.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newbuy.in.DTO.ApiResponse;
import com.newbuy.in.model.Users;
import com.newbuy.in.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	 @PatchMapping("/{email}/update-profile")
	    public ResponseEntity<ApiResponse<Users>> updateProfile(
	            @PathVariable String email,
	            @RequestBody Users updateData) {

	        ApiResponse<Users> response = userService.updateUserProfile(email, updateData);
	    	if (!response.isSts()) {
				return ResponseEntity.badRequest().body(response);
			}
			return ResponseEntity.ok(response);
	    }
	 @GetMapping("/get-profile-details/{email}")
	 public ResponseEntity<ApiResponse<Users>> getUserProfile(@PathVariable String email)
	 {
		 ApiResponse<Users> response = userService.getUserProfile(email);
			if (!response.isSts()) {
				return ResponseEntity.badRequest().body(response);
			}
			return ResponseEntity.ok(response);
		 
	 }
}
