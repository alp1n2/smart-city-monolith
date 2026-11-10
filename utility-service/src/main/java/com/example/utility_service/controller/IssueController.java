package com.example.utility_service.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.example.utility_service.dto.CreateIssueRequest;
import com.example.utility_service.dto.IssueResponse;
import com.example.utility_service.dto.UpdateIssueStatusRequest;
import com.example.utility_service.service.IssueService;

import java.util.List;

@RestController
@RequestMapping("/issues")
@RequiredArgsConstructor
public class IssueController {

    private final IssueService issueService;

    @PostMapping
    public ResponseEntity<IssueResponse> create(@Valid @RequestBody CreateIssueRequest request,
                                                Authentication auth) {
        Long userId = Long.parseLong(auth.getName());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(issueService.create(userId, request));
    }

    @GetMapping
    public ResponseEntity<List<IssueResponse>> getAll(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String category) {
        return ResponseEntity.ok(issueService.getAll(status, category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<IssueResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(issueService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IssueResponse> updateStatus(@PathVariable Long id,
                                                      @Valid @RequestBody UpdateIssueStatusRequest request) {
        return ResponseEntity.ok(issueService.updateStatus(id, request));
    }
}