package com.example.transport_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "parking_reservations")
@Getter
@Setter
public class ParkingReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "parking_id", nullable = false)
    private Long parkingId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "vehicle_number", nullable = false, length = 20)
    private String vehicleNumber;

    @Column(name = "reserved_from", nullable = false)
    private LocalDateTime reservedFrom;

    @Column(name = "reserved_to", nullable = false)
    private LocalDateTime reservedTo;

    @Column(nullable = false, length = 20)
    private String status = "ACTIVE";

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}