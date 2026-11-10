package com.example.environment_service.dto;

import com.example.environment_service.entity.SensorData;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class SensorDataResponse {
    private Long id;
    private Long sensorId;
    private Double value;
    private String unit;
    private LocalDateTime recordedAt;

    public static SensorDataResponse from(SensorData d) {
        return new SensorDataResponse(
                d.getId(), d.getSensorId(), d.getValue(),
                d.getUnit(), d.getRecordedAt()
        );
    }
}
