package com.example.utility_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import com.example.utility_service.entity.Issue;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class IssueResponse {
    private Long id;
    private Long userId;
    private Long assignedTo;
    private String category;
    private String title;
    private String description;
    private String address;
    private Double latitude;
    private Double longitude;
    private String status;
    private String priority;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static IssueResponse from(Issue i) {
        return new IssueResponse(
                i.getId(), i.getUserId(), i.getAssignedTo(),
                i.getCategory(), i.getTitle(), i.getDescription(),
                i.getAddress(), i.getLatitude(), i.getLongitude(),
                i.getStatus(), i.getPriority(),
                i.getCreatedAt(), i.getUpdatedAt()
        );
    }
}