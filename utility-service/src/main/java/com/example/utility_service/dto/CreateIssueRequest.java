package com.example.utility_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateIssueRequest {

    @NotBlank
    private String category;

    @NotBlank
    private String title;

    private String description;

    @NotBlank
    private String address;

    private Double latitude;
    private Double longitude;

    private String priority;
}