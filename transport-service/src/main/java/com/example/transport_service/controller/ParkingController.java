package com.example.transport_service.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.example.transport_service.dto.ParkingResponse;
import com.example.transport_service.dto.ReservationResponse;
import com.example.transport_service.dto.ReserveRequest;
import com.example.transport_service.service.ParkingService;

import java.util.List;

@RestController
@RequestMapping("/parking")
@RequiredArgsConstructor
public class ParkingController {

    private final ParkingService parkingService;

    @GetMapping
    public ResponseEntity<List<ParkingResponse>> getAll() {
        return ResponseEntity.ok(parkingService.getAll());
    }

    @PostMapping("/{id}/reserve")
    public ResponseEntity<ReservationResponse> reserve(@PathVariable Long id,
                                                       @Valid @RequestBody ReserveRequest request,
                                                       Authentication auth) {
        Long userId = Long.parseLong(auth.getName());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(parkingService.reserve(id, userId, request));
    }
}