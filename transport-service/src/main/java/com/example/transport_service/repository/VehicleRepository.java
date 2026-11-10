package com.example.transport_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.transport_service.entity.Vehicle;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByStatus(String status);
}