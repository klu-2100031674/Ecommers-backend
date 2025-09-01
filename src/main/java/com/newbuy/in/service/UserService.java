package com.newbuy.in.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newbuy.in.DTO.*;
import com.newbuy.in.model.UserStaging;
import com.newbuy.in.model.Users;
import com.newbuy.in.repository.UserRepo;
import com.newbuy.in.repository.UserStagingRepo;
import com.newbuy.in.utils.EmailTemplateUtil;
import com.newbuy.in.utils.Utils;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private EmailService emailService;

	@Autowired
	private UserStagingRepo userStagingRepo;

	@Autowired
	private Utils utils;

	// Login method

	public ApiResponse<LoginResponse> loginUser(String email, String password) {
		Users user = userRepo.login(email, password);

		if (user == null) {
			return new ApiResponse<>(false, null, "Login failed");
		}

		// Generate JWT token
		System.out.println("check 1"+(user.getAdmin() == "true" ? true : false));
		String token = utils.generateToken(user.getEmail(), (user.getAdmin() == "true" ? true : false));

		// Prepare response object
		LoginResponse loginResponse = new LoginResponse(token, user.getEmail(), user.getName());

		return new ApiResponse<>(true, loginResponse, "Login successful");
	}

	// check email
	public ApiResponse<Void> verifyEmail(String email) {
		if (userRepo.findByEmail(email) != null) {
			return new ApiResponse<>(false, null, "User is already registered");
		}
		return new ApiResponse<>(true, null, "User is not registered");
	}

	// OTP generation method
	public ApiResponse<UserStaging> generateOtp(String name, String email, String mobileNumber, String password) {
		// Generate 6-digit OTP
		String otp = Utils.generateOTP(6);

		// Check if user already exists in staging
		UserStaging userStaging = userStagingRepo.findByEmail(email);
		if (userStaging == null) {
			userStaging = new UserStaging(name, email, mobileNumber, password, otp);
		} else {
			userStaging.setOtp(otp);
			userStaging.setName(name);
			userStaging.setMobileNumber(mobileNumber);
			userStaging.setPassword(password);
		}
		userStagingRepo.save(userStaging);
		// Send email
		String subject = "Your OTP Code";
		String body = EmailTemplateUtil.otpTemplate(name, otp);
		emailService.sendHtmlEmail(email, subject, body);

		return new ApiResponse<>(true, userStaging, "OTP generated and sent successfully");
	}

	// Verify OTP
	public ApiResponse<Void> verifyOtp(String email, String otp) {
		UserStaging userStaging = userStagingRepo.findByEmail(email);

		if (userStaging == null) {
			return new ApiResponse<>(false, null, "No OTP request found for this email");
		}

		if (!userStaging.getOtp().equals(otp)) {
			return new ApiResponse<>(false, null, "Invalid OTP");
		}

		// OTP is correct → move to Users table
		Users user = new Users();
		user.setName(userStaging.getName());
		user.setEmail(userStaging.getEmail());
		user.setNumber(userStaging.getMobileNumber());
		user.setPassword(userStaging.getPassword());
		user.setAdmin("false");
		user.setActive(true);
		// TODO: Set default password if needed or handle login differently

		userRepo.save(user);
		userStagingRepo.delete(userStaging); // remove from staging

		return new ApiResponse<>(true, null, "OTP verified successfully and user registered");
	}
}
