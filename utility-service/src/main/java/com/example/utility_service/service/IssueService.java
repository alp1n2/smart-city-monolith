package com.example.utility_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.utility_service.dto.CreateIssueRequest;
import com.example.utility_service.dto.IssueResponse;
import com.example.utility_service.dto.UpdateIssueStatusRequest;
import com.example.utility_service.entity.Issue;
import com.example.utility_service.repository.IssueRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IssueService {

    private final IssueRepository issueRepository;

    @Transactional
    public IssueResponse create(Long userId, CreateIssueRequest request) {
        Issue issue = new Issue();
        issue.setUserId(userId);
        issue.setCategory(request.getCategory());
        issue.setTitle(request.getTitle());
        issue.setDescription(request.getDescription());
        issue.setAddress(request.getAddress());
        issue.setLatitude(request.getLatitude());
        issue.setLongitude(request.getLongitude());
        issue.setPriority(request.getPriority() != null ? request.getPriority() : "MEDIUM");
        issue.setStatus("NEW");

        return IssueResponse.from(issueRepository.save(issue));
    }

    public List<IssueResponse> getAll(String status, String category) {
        List<Issue> issues;

        if (status != null && !status.isBlank()) {
            issues = issueRepository.findByStatus(status);
        } else if (category != null && !category.isBlank()) {
            issues = issueRepository.findByCategory(category);
        } else {
            issues = issueRepository.findAll();
        }

        return issues.stream().map(IssueResponse::from).toList();
    }

    public IssueResponse getById(Long id) {
        return issueRepository.findById(id)
                .map(IssueResponse::from)
                .orElseThrow(() -> new IllegalArgumentException("Заявка не найдена"));
    }

    @Transactional
    public IssueResponse updateStatus(Long id, UpdateIssueStatusRequest request) {
        Issue issue = issueRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Заявка не найдена"));

        issue.setStatus(request.getStatus());
        if (request.getAssignedTo() != null) {
            issue.setAssignedTo(request.getAssignedTo());
        }

        return IssueResponse.from(issueRepository.save(issue));
    }
}