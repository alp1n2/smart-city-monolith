package com.example.identity_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.identity_service.dto.UserResponse;
import com.example.identity_service.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse getById(Long id) {
        return userRepository.findById(id)
                .map(UserResponse::from)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден"));
    }

    public List<UserResponse> getAll() {
        return userRepository.findAll().stream()
                .map(UserResponse::from)
                .toList();
    }
}