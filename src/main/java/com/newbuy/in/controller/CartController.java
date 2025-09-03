package com.newbuy.in.controller;

import com.newbuy.in.DTO.ApiResponse;
import com.newbuy.in.model.Cart;
import com.newbuy.in.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // Add product to cart
    @PostMapping("/add")
    public ResponseEntity<ApiResponse<Cart>> addToCart(
            @RequestParam int userId,
            @RequestParam Long productId,
            @RequestParam int quantity) {

        ApiResponse<Cart> response = cartService.addToCart(userId, productId, quantity);
        return ResponseEntity.ok(response);
    }

    // Get cart items by user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Cart>> getCartByUser(@PathVariable int userId) {
        return ResponseEntity.ok(cartService.getCartByUser(userId));
    }

    // Remove item from cart
    @DeleteMapping("/{cartId}")
    public ResponseEntity<String> removeFromCart(@PathVariable Long cartId) {
        cartService.removeFromCart(cartId);
        return ResponseEntity.ok("Item removed from cart successfully");
    }
}
