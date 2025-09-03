package com.newbuy.in.DTO;

public class LoginResponse {
    private String token;
    private String email;
    private String name;
    private String admin;

    public LoginResponse() {}

    public LoginResponse(String token, String email, String name,String admin) {
        this.token = token;
        this.email = email;
        this.name = name;
        this.admin=admin;
    }

    // Getters & Setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAdmin() { return admin; }
    public void setAdmin(String admin) { this.name = admin; }
}
