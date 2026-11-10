package com.example.identity_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.identity_service.entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
}