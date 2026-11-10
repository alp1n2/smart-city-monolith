package com.example.notification_service.dto;

import com.example.notification_service.entity.Notification;

import java.time.LocalDateTime;

public class NotificationResponse {
    private Long id;
    private Long userId;
    private String channel;
    private String subject;
    private String message;
    private String status;
    private String sourceService;
    private LocalDateTime createdAt;
    private LocalDateTime sentAt;

    public NotificationResponse(Long id, Long userId, String channel, String subject,
                                String message, String status, String sourceService,
                                LocalDateTime createdAt, LocalDateTime sentAt) {
        this.id = id;
        this.userId = userId;
        this.channel = channel;
        this.subject = subject;
        this.message = message;
        this.status = status;
        this.sourceService = sourceService;
        this.createdAt = createdAt;
        this.sentAt = sentAt;
    }

    public static NotificationResponse from(Notification n) {
        return new NotificationResponse(
                n.getId(), n.getUserId(), n.getChannel(), n.getSubject(),
                n.getMessage(), n.getStatus(), n.getSourceService(),
                n.getCreatedAt(), n.getSentAt()
        );
    }

    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public String getChannel() { return channel; }
    public String getSubject() { return subject; }
    public String getMessage() { return message; }
    public String getStatus() { return status; }
    public String getSourceService() { return sourceService; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getSentAt() { return sentAt; }
}
