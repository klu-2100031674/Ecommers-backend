package com.newbuy.in.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newbuy.in.model.Products;
import com.newbuy.in.service.ProductService;
import com.newbuy.in.DTO.*;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/insert")
	public ResponseEntity<ApiResponse<Products>> saveProduct(@Valid @RequestBody Products product) {
		ApiResponse<Products> response = productService.saveProduct(product);
		return ResponseEntity.ok(response);
	}

}
