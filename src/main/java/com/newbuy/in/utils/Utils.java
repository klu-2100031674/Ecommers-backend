package com.newbuy.in.utils;

import java.security.SecureRandom;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;
import java.nio.charset.StandardCharsets;

@Component
public class Utils {

    // OTP generation
    private static final String DIGITS = "0123456789";
    private static final SecureRandom random = new SecureRandom();

    public static String generateOTP(int length) {
        if (length <= 0) throw new IllegalArgumentException("OTP length must be greater than 0");
        StringBuilder otp = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            otp.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        }
        return otp.toString();
    }

    // JWT constants (In a real app, this should be an env variable)
    private final String SECRET_KEY = "ThisIsASuperSecretKeyThatShouldBeAtLeast256BitsLongForHS256";
    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    private Key getSigningKey() {
        // Ensure the key is long enough for HS256 (256 bits or 32 bytes)
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    // JWT methods
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            System.err.println("Token validation failed: " + e.getMessage());
            return false;
        }
    }

    public String getEmailFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // For testing
    public static void main(String[] args) {
        Utils utils = new Utils();
        System.out.println("Generated OTP: " + generateOTP(6));

        // Testing JWT functionality
        String email = "test@example.com";
        String token = utils.generateToken(email);
        System.out.println("Generated JWT Token: " + token);

        System.out.println("Validating Token: " + utils.validateToken(token));

        String extractedEmail = utils.getEmailFromToken(token);
        System.out.println("Email from Token: " + extractedEmail);
    }
}