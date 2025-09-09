package com.newbuy.in.service;

import com.newbuy.in.DTO.ApiResponse;
import com.newbuy.in.model.Cart;
import com.newbuy.in.model.Products;
import com.newbuy.in.model.Users;
import com.newbuy.in.repository.CartRepo;
import com.newbuy.in.repository.ProductRepo;
import com.newbuy.in.repository.UserRepo;  // You’ll need a UserRepo for Users entity
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepo cartRepo;
    private final ProductRepo productRepo;
    private final UserRepo userRepo;

    public CartService(CartRepo cartRepo, ProductRepo productRepo, UserRepo userRepo) {
        this.cartRepo = cartRepo;
        this.productRepo = productRepo;
        this.userRepo = userRepo;
    }

    // Add product to cart
    public ApiResponse<Cart> addToCart(int userId, Long productId, int quantity) {
        Users user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Products product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Check if product already exists in cart
        Cart existingCart = cartRepo.findByUserAndProduct(user, product);
        if (existingCart != null) {
            existingCart.setQuantity(existingCart.getQuantity() + quantity);
            Cart updatedCart = cartRepo.save(existingCart);
            return new ApiResponse<>(true, updatedCart, "Cart updated with more quantity");
        }

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setProduct(product);
        cart.setQuantity(quantity);

        Cart savedCart = cartRepo.save(cart);
        return new ApiResponse<>(true, savedCart, "Product added to cart");
    }


    // Get all cart items for a user
    public ApiResponse<List<Cart>> getCartByUser(int userId) {
        Users user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Cart> cartItems = cartRepo.findByUser(user);

        if (cartItems.isEmpty()) {
            return new ApiResponse<>(true, cartItems, "Cart is empty for user ID: " + userId);
        }

        return new ApiResponse<>(true, cartItems, "Cart fetched successfully");
    }


    // Remove item from cart
    public void removeFromCart(Long cartId) {
        cartRepo.deleteById(cartId);
    }
}
