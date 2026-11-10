package com.example.transport_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import com.example.transport_service.entity.ParkingReservation;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ReservationResponse {
    private Long id;
    private Long parkingId;
    private Long userId;
    private String vehicleNumber;
    private LocalDateTime reservedFrom;
    private LocalDateTime reservedTo;
    private String status;

    public static ReservationResponse from(ParkingReservation r) {
        return new ReservationResponse(
                r.getId(), r.getParkingId(), r.getUserId(),
                r.getVehicleNumber(), r.getReservedFrom(),
                r.getReservedTo(), r.getStatus()
        );
    }
}