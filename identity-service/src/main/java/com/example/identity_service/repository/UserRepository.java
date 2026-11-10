package com.example.identity_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.identity_service.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}