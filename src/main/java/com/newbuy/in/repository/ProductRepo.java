package com.newbuy.in.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.newbuy.in.model.Products;

@Repository
public interface ProductRepo extends JpaRepository<Products, Long>  {

}
