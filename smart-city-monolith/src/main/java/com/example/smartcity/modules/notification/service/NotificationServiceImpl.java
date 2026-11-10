package com.example.smartcity.modules.notification.service;

import com.example.smartcity.modules.notification.api.NotificationApi;
import com.example.smartcity.modules.notification.entity.Notification;
import com.example.smartcity.modules.notification.repository.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class NotificationServiceImpl implements NotificationApi {

    private static final Logger log = LoggerFactory.getLogger(NotificationServiceImpl.class);
    private final NotificationRepository repository;

    public NotificationServiceImpl(NotificationRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public void send(Long userId, String channel, String subject, String message, String sourceService) {
        Notification n = new Notification();
        n.setUserId(userId);
        n.setChannel(channel);
        n.setSubject(subject);
        n.setMessage(message);
        n.setSourceService(sourceService);
        n.setStatus("SENT");
        n.setSentAt(LocalDateTime.now());

        repository.save(n);
        log.info("Уведомление отправлено: user={}, source={}", userId, sourceService);
    }
}