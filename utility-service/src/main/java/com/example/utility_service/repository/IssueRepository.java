package com.example.utility_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.utility_service.entity.Issue;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findByUserId(Long userId);
    List<Issue> findByStatus(String status);
    List<Issue> findByCategory(String category);
}