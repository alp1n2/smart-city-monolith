package com.example.notification_service.service;

import com.example.notification_service.dto.NotificationResponse;
import com.example.notification_service.dto.SendNotificationRequest;
import com.example.notification_service.entity.Notification;
import com.example.notification_service.repository.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class NotificationService {

    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Transactional
    public NotificationResponse send(SendNotificationRequest request) {
        Notification notification = new Notification();
        notification.setUserId(request.getUserId());
        notification.setChannel(request.getChannel());
        notification.setSubject(request.getSubject());
        notification.setMessage(request.getMessage());
        notification.setSourceService(request.getSourceService());

        // Имитация отправки: сразу помечаем как SENT
        notification.setStatus("SENT");
        notification.setSentAt(LocalDateTime.now());

        Notification saved = notificationRepository.save(notification);

        log.info("Уведомление отправлено: user={}, channel={}, source={}",
                saved.getUserId(), saved.getChannel(), saved.getSourceService());

        return NotificationResponse.from(saved);
    }
}
