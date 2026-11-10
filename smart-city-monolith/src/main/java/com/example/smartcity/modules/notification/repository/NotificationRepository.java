package com.example.smartcity.modules.notification.repository;

import com.example.smartcity.modules.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}