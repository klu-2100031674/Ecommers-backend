package com.newbuy.in.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.newbuy.in.model.UserStaging;

@Repository
public interface UserStagingRepo extends JpaRepository<UserStaging, Integer> {
    UserStaging findByEmail(String email);
}

