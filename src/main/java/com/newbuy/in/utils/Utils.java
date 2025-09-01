package com.newbuy.in.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Utils {

    // OTP generation
    private static final String DIGITS = "0123456789";
    private static final SecureRandom random = new SecureRandom();

    public static String generateOTP(int length) {
        if (length <= 0)
            throw new IllegalArgumentException("OTP length must be greater than 0");
        StringBuilder otp = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            otp.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        }
        return otp.toString();
    }

    // JWT constants (should ideally come from environment variables)
    private final String SECRET_KEY = "ThisIsASuperSecretKeyThatShouldBeAtLeast256BitsLongForHS256";
    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    // Generate JWT token with role claim
    public String generateToken(String email, boolean isAdmin) {
        return Jwts.builder()
                .setSubject(email)
                .claim("roles", isAdmin ? List.of("ADMIN") : List.of("USER"))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Validate JWT
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            System.err.println("Token validation failed: " + e.getMessage());
            return false;
        }
    }

    // Extract email (subject) from token
    public String getEmailFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody().getSubject();
    }

    // Extract roles from token
    public List<SimpleGrantedAuthority> getRolesFromToken(String token) {
        List<String> roles = Jwts.parserBuilder().setSigningKey(getSigningKey()).build()
                .parseClaimsJws(token).getBody().get("roles", List.class);

        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
    }

    // For testing
    public static void main(String[] args) {
        Utils utils = new Utils();
        System.out.println("Generated OTP: " + generateOTP(6));

        String email = "test@example.com";
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYXR0YWppdGhlbmRyYTA3QGdtYWlsLmNvbSIsInJvbGVzIjpbIlVTRVIiXSwiaWF0IjoxNzU2NzM4NzAyLCJleHAiOjE3NTY3NDIzMDJ9.zvxd6RWtBgcQQ5CqUQSasalLlR1gofOUOE29Alg0Epo";
        System.out.println("Generated JWT Token: " + token);

        System.out.println("Validating Token: " + utils.validateToken(token));
        System.out.println("Email from Token: " + utils.getEmailFromToken(token));
        System.out.println("Roles from Token: " + utils.getRolesFromToken(token));
    }
}
