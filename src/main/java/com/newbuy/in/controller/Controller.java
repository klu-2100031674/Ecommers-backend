package com.newbuy.in.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newbuy.in.DTO.*;
import com.newbuy.in.model.UserStaging;
import com.newbuy.in.model.Users;
import com.newbuy.in.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/public/users")
public class Controller {

	private final UserService userService;

	public Controller(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/page")
	public ResponseEntity<String> check() {
		return ResponseEntity.ok("check ramesh");
	}

	@GetMapping("/check-email")
	public ResponseEntity<ApiResponse<Void>> checkEmail(@RequestParam String email) {
		ApiResponse<Void> response = userService.verifyEmail(email);
		if (!response.isSts()) {
			return ResponseEntity.badRequest().body(response); // 400 for failure
		}
		return ResponseEntity.ok(response); // 200 for success
	}

	@PostMapping("/generate-otp")
	public ResponseEntity<ApiResponse<UserStaging>> generateOtp(@RequestBody Register register) {
		ApiResponse<UserStaging> response = userService.generateOtp(register.getName(), register.getEmail(),
				register.getMobileNumber(), register.getPassword());

		if (!response.isSts()) {
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}

	@PostMapping("/verify-otp")
	public ResponseEntity<ApiResponse<Void>> verifyOtp(@RequestParam String email, @RequestParam String otp) {

		ApiResponse<Void> response = userService.verifyOtp(email, otp);
		if (!response.isSts()) {
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}

	@PostMapping("/login")
	public ResponseEntity<ApiResponse<LoginResponse>> loginUser(@RequestBody Map<String, Object> payload) {
		String email = (String) payload.get("email");
		String password = (String) payload.get("password");

		ApiResponse<LoginResponse> response = userService.loginUser(email, password);
		if (!response.isSts()) {
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}

	@GetMapping("/forget-password")
	public ResponseEntity<ApiResponse<Void>> forgetPasswordOtpGeneration(@RequestParam String email) {
		ApiResponse<Void> response = userService.forgetPasswordGenerate(email);
		if (!response.isSts()) {
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}

	@PostMapping("/forget-password-verify-otp")
	public ResponseEntity<ApiResponse<Void>> forgetPasswordOtpGeneration(@RequestBody Map<String, Object> payload) {
		String email = (String) payload.get("email");
		String otp = (String) payload.get("otp");

		ApiResponse<Void> response = userService.verifyForgetPasswordOtp(email, otp);

		if (!response.isSts()) {
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);

	}
	@PostMapping("/forget-password-update")
	public ResponseEntity<ApiResponse<Void>> updateUserPassword(@RequestBody Map<String, Object> payload) {
		String email = (String) payload.get("email");
		String password = (String) payload.get("password");

		ApiResponse<Void> response = userService.updateUserPassword(email, password);

		if (!response.isSts()) {
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);

	}
}
