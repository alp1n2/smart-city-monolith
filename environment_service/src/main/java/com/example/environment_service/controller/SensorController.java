package com.example.environment_service.controller;

import com.example.environment_service.dto.IngestDataRequest;
import com.example.environment_service.dto.SensorDataResponse;
import com.example.environment_service.dto.SensorResponse;
import com.example.environment_service.service.SensorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/sensors")
@RequiredArgsConstructor
public class SensorController {

    private final SensorService sensorService;

    @GetMapping
    public ResponseEntity<List<SensorResponse>> getAll(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String status) {
        return ResponseEntity.ok(sensorService.getAllSensors(type, status));
    }

    @GetMapping("/{id}/data")
    public ResponseEntity<List<SensorDataResponse>> getData(
            @PathVariable Long id,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        return ResponseEntity.ok(sensorService.getSensorData(id, from, to));
    }

    @PostMapping("/data")
    public ResponseEntity<SensorDataResponse> ingest(
            @Valid @RequestBody IngestDataRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(sensorService.ingest(request));
    }
}