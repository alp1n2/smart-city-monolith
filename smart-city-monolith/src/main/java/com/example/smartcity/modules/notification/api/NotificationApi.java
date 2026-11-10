package com.example.smartcity.modules.notification.api;

public interface NotificationApi {
    void send(Long userId, String channel, String subject, String message, String sourceService);
}