package com.newbuy.in.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.newbuy.in.model.ForgetPassword;
import java.util.List;


public interface ForgetPasswordRepo extends JpaRepository<ForgetPassword, Long> {
	ForgetPassword findByEmail(String email);
	void deleteByEmail(String email);

}
