package com.newbuy.in.DTO;

public class LoginResponse {
    private String token;
    private String email;
    private String name;

    public LoginResponse() {}

    public LoginResponse(String token, String email, String name) {
        this.token = token;
        this.email = email;
        this.name = name;
    }

    // Getters & Setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
