package com.newbuy.in.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "forget_passwords")
public class ForgetPassword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
	@Column(name="email",nullable=false,length = 50,unique=true)
    private String email;

    @NotBlank(message = "OTP is required")
    @Column(nullable = false)
    private String otp;

    public ForgetPassword() {}

    public ForgetPassword(String email, String otp) {
        this.email = email;
        this.otp = otp;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
