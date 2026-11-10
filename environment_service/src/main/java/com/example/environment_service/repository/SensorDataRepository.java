package com.example.environment_service.repository;

import com.example.environment_service.entity.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SensorDataRepository extends JpaRepository<SensorData, Long> {
    List<SensorData> findBySensorIdOrderByRecordedAtDesc(Long sensorId);
    List<SensorData> findBySensorIdAndRecordedAtBetween(Long sensorId,
                                                        LocalDateTime from,
                                                        LocalDateTime to);
}