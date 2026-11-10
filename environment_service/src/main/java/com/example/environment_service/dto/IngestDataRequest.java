package com.example.environment_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class IngestDataRequest {

    @NotNull
    private Long sensorId;

    @NotNull
    private Double value;

    @NotNull
    private String unit;

    private LocalDateTime recordedAt;
}