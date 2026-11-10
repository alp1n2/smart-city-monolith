package com.example.environment_service.repository;

import com.example.environment_service.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
    List<Sensor> findByType(String type);
    List<Sensor> findByStatus(String status);
}
