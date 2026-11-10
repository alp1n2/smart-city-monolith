package com.example.transport_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import  com.example.transport_service.dto.VehicleResponse;
import  com.example.transport_service.repository.VehicleRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public List<VehicleResponse> getAll() {
        return vehicleRepository.findAll().stream()
                .map(VehicleResponse::from)
                .toList();
    }
}