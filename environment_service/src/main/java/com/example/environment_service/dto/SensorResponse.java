package com.example.environment_service.dto;

import com.example.environment_service.entity.Sensor;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class SensorResponse {
    private Long id;
    private String name;
    private String type;
    private String location;
    private Double latitude;
    private Double longitude;
    private String status;
    private LocalDateTime installedAt;

    public static SensorResponse from(Sensor s) {
        return new SensorResponse(
                s.getId(), s.getName(), s.getType(), s.getLocation(),
                s.getLatitude(), s.getLongitude(),
                s.getStatus(), s.getInstalledAt()
        );
    }
}