package com.newbuy.in.repository;

import com.newbuy.in.model.Cart;
import com.newbuy.in.model.Products;
import com.newbuy.in.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {

    List<Cart> findByUser(Users user);

	Cart findByUserAndProduct(Users user, Products product);
}
