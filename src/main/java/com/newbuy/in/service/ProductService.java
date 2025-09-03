package com.newbuy.in.service;

import com.newbuy.in.DTO.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.newbuy.in.model.ProductImage;
import com.newbuy.in.model.Products;
import com.newbuy.in.repository.ProductRepo;
import com.newbuy.in.utils.Utils;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

	private final ProductRepo productRepo;
	
	private final Utils util;

	public ProductService(ProductRepo productRepo) {
		this.productRepo = productRepo;
		this.util = new Utils();
	}

	// Create or update product
	public ApiResponse<Products> saveProduct(Products product) {
		System.out.println(util.getRolesFromToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYXR0YWppdGhlbmRyYTA3QGdtYWlsLmNvbSIsInJvbGVzIjpbIlVTRVIiXSwiaWF0IjoxNzU2NzM4NzAyLCJleHAiOjE3NTY3NDIzMDJ9.zvxd6RWtBgcQQ5CqUQSasalLlR1gofOUOE29Alg0Epo"));
	    // set back-reference for all images
	    if (product.getImages() != null) {
	        for (ProductImage img : product.getImages()) {
	            img.setProduct(product);
	        }
	    }

		Products savedProduct = productRepo.save(product);
		return new ApiResponse<>(true, savedProduct, "Product saved successfully");

	}
    public ApiResponse<Page<Products>> getAllProducts(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) 
                        ? Sort.by(sortBy).ascending() 
                        : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Products> productsPage = productRepo.findAll(pageable);

        return new ApiResponse<>(true, productsPage, "Products fetched successfully with pagination");
    }

	// Get product by ID
	public Products getProductById(Long id) {
		return productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
	}

	// Get all products
	public List<Products> getAllProducts() {
		return productRepo.findAll();
	}

	// Delete product by ID
	public void deleteProduct(Long id) {
		productRepo.deleteById(id);
	}
}
