package com.example.transport_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.transport_service.entity.ParkingReservation;

import java.util.List;

public interface ParkingReservationRepository extends JpaRepository<ParkingReservation, Long> {
    List<ParkingReservation> findByUserId(Long userId);
}