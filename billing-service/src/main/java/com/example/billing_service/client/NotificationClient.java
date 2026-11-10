package com.example.billing_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "notification-service", url = "${services.notification.url}")
public interface NotificationClient {

    @PostMapping("/notifications")
    void sendNotification(@RequestBody Map<String, Object> request);
}
