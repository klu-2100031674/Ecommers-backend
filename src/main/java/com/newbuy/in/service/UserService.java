package com.newbuy.in.service;

import java.lang.reflect.Field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newbuy.in.DTO.*;
import com.newbuy.in.model.ForgetPassword;
import com.newbuy.in.model.UserStaging;
import com.newbuy.in.model.Users;
import com.newbuy.in.repository.ForgetPasswordRepo;
import com.newbuy.in.repository.UserRepo;
import com.newbuy.in.repository.UserStagingRepo;
import com.newbuy.in.utils.EmailTemplateUtil;
import com.newbuy.in.utils.Utils;

import jakarta.transaction.Transactional;

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

	@Autowired
	private ForgetPasswordRepo forgetPasswordRepo;

	// Login method

	public ApiResponse<LoginResponse> loginUser(String email, String password) {
		Users user = userRepo.login(email, password);

		if (user == null) {
			return new ApiResponse<>(false, null, "Login failed");
		}

		// Generate JWT token
		System.out.println("check 1" + (user.getAdmin() == "true" ? true : false));
		String token = utils.generateToken(user.getEmail(), (user.getAdmin() == "true" ? true : false));

		// Prepare response object
		LoginResponse loginResponse = new LoginResponse(token, user.getEmail(), user.getName(), user.getAdmin(),user.getId());

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

	// Forget password
	@Transactional
	public ApiResponse<Void> forgetPasswordGenerate(String email) {
		Users user = userRepo.findByEmail(email);
		if (user == null) {
			return new ApiResponse<>(false, null, "User is Not registered");
		}

		String otp = Utils.generateOTP(6);

		ForgetPassword staging_data = forgetPasswordRepo.findByEmail(email);
		if (staging_data != null) {
			staging_data.setOtp(otp);
		} else {
			staging_data = new ForgetPassword(email, otp);
		}

		forgetPasswordRepo.save(staging_data);

		// If this throws an exception, transaction rolls back automatically
		String subject = "Your OTP Code";
		String body = EmailTemplateUtil.otpTemplate(user.getName(), otp);
		emailService.sendHtmlEmail(email, subject, body);

		return new ApiResponse<>(true, null, "Otp sent successfully");
	}

	public ApiResponse<Void> verifyForgetPasswordOtp(String email, String otp) {
		ForgetPassword staging_data = forgetPasswordRepo.findByEmail(email);
		if (staging_data.getOtp().equals(otp)) {
			return new ApiResponse<>(true, null, "Otp Verified");
		}
		return new ApiResponse<>(false, null, "Invalid OTP");
	}

	@Transactional
	public ApiResponse<Void> updateUserPassword(String email, String password) {
		System.out.println("came");
		forgetPasswordRepo.deleteByEmail(email);
		Users userData = userRepo.findByEmail(email);
		if (userData.getPassword().equals(password))
			return new ApiResponse<>(false, null,
					"present password and previous passord is same choose different password");
		userData.setPassword(password);
		userRepo.save(userData);
		return new ApiResponse<>(true, null, "password updated successfully");

	}

	@Transactional
	public ApiResponse<Users> updateUserProfile(String email, Users updateData) {
		Users user = userRepo.findByEmail(email);
		// Update only if new value is present (null = skip)
		if (updateData.getName() != null)
			user.setName(updateData.getName());
		if (updateData.getNumber() != null)
			user.setNumber(updateData.getNumber());
		if (updateData.getAddress_Line_1() != null)
			user.setAddress_Line_1(updateData.getAddress_Line_1());
		if (updateData.getAddress_Line_2() != null)
			user.setAddress_Line_2(updateData.getAddress_Line_2());
		if (updateData.getPostcode() != null)
			user.setPostcode(updateData.getPostcode());
		if (updateData.getState() != null)
			user.setState(updateData.getState());
		if (updateData.getCity() != null)
			user.setCity(updateData.getCity());
		if (updateData.getCountry() != null)
			user.setCountry(updateData.getCountry());
		// email, password, admin, active are intentionally skipped

		Users updatedUser = userRepo.save(user);
		return new ApiResponse<>(true, updatedUser, "Profile updated successfully");
	}

	public ApiResponse<Users> getUserProfile(String email) {
		Users user = userRepo.findByEmail(email);
		if (user == null)
			return new ApiResponse<>(false, null, "Unable to process request");
		return new ApiResponse<>(true, user, "user profile fetched successfully");
	}
}
