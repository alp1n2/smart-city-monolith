package com.example.transport_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.transport_service.entity.Parking;

public interface ParkingRepository extends JpaRepository<Parking, Long> {
}