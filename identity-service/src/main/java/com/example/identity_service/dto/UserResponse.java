package com.example.identity_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import com.example.identity_service.entity.User;

@Getter
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String email;
    private String fullName;
    private String phone;
    private String role;

    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getFullName(),
                user.getPhone(),
                user.getRole()
        );
    }
}