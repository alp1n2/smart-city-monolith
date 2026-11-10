package com.example.environment_service.service;

import com.example.environment_service.dto.IngestDataRequest;
import com.example.environment_service.dto.SensorDataResponse;
import com.example.environment_service.dto.SensorResponse;
import com.example.environment_service.entity.Sensor;
import com.example.environment_service.entity.SensorData;
import com.example.environment_service.repository.SensorDataRepository;
import com.example.environment_service.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SensorService {

    private final SensorRepository sensorRepository;
    private final SensorDataRepository sensorDataRepository;

    public List<SensorResponse> getAllSensors(String type, String status) {
        List<Sensor> sensors;

        if (type != null && !type.isBlank()) {
            sensors = sensorRepository.findByType(type);
        } else if (status != null && !status.isBlank()) {
            sensors = sensorRepository.findByStatus(status);
        } else {
            sensors = sensorRepository.findAll();
        }

        return sensors.stream().map(SensorResponse::from).toList();
    }

    public List<SensorDataResponse> getSensorData(Long sensorId,
                                                  LocalDateTime from,
                                                  LocalDateTime to) {
        if (!sensorRepository.existsById(sensorId)) {
            throw new IllegalArgumentException("Датчик не найден");
        }

        List<SensorData> data;
        if (from != null && to != null) {
            data = sensorDataRepository.findBySensorIdAndRecordedAtBetween(sensorId, from, to);
        } else {
            data = sensorDataRepository.findBySensorIdOrderByRecordedAtDesc(sensorId);
        }

        return data.stream().map(SensorDataResponse::from).toList();
    }

    @Transactional
    public SensorDataResponse ingest(IngestDataRequest request) {
        Sensor sensor = sensorRepository.findById(request.getSensorId())
                .orElseThrow(() -> new IllegalArgumentException("Датчик не найден"));

        if (!"ACTIVE".equals(sensor.getStatus())) {
            throw new IllegalArgumentException("Датчик неактивен");
        }

        SensorData data = new SensorData();
        data.setSensorId(request.getSensorId());
        data.setValue(request.getValue());
        data.setUnit(request.getUnit());
        data.setRecordedAt(request.getRecordedAt() != null
                ? request.getRecordedAt()
                : LocalDateTime.now());

        return SensorDataResponse.from(sensorDataRepository.save(data));
    }
}
