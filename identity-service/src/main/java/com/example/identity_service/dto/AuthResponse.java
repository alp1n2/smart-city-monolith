package com.example.identity_service.dto;

import lombok.Getter;

@Getter
public class AuthResponse {
    private final String token;
    private final String type = "Bearer";
    private final Long userId;
    private final String email;
    private final String role;

    public AuthResponse(String token, Long userId, String email, String role) {
        this.token = token;
        this.userId = userId;
        this.email = email;
        this.role = role;
    }
}