package com.example.utility_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateIssueStatusRequest {

    @NotBlank
    private String status;

    private Long assignedTo;
}