package com.newbuy.in.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "user_staging")
public class UserStaging {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "staging_id")
	private int id;

	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@Column(name = "email", nullable = false, unique = true, length = 50)
	private String email;

	@Column(name = "mobile_number", nullable = false, length = 15)
	private String mobileNumber;

	@Column(name = "password", nullable = false, length = 18)
	@JsonIgnore
	private String password;

	@Column(name = "otp", nullable = false, length = 10)
	@JsonIgnore
	private String otp;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserStaging(int id, String name, String email, String mobileNumber, String password, String otp) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.password = password;
		this.otp = otp;
	}

	// Constructors
	public UserStaging() {
	}

	public UserStaging(String name, String email, String mobileNumber, String password, String otp) {
		this.name = name;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.password = password;
		this.otp = otp;
	}

	// Getters & Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	@Override
	public String toString() {
		return "UserStaging [id=" + id + ", name=" + name + ", email=" + email + ", mobileNumber=" + mobileNumber
				+ ", password=" + password + ", otp=" + otp + "]";
	}
}
