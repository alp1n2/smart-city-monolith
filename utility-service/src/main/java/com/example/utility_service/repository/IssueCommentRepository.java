package com.example.utility_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.utility_service.entity.IssueComment;

import java.util.List;

public interface IssueCommentRepository extends JpaRepository<IssueComment, Long> {
    List<IssueComment> findByIssueId(Long issueId);
}