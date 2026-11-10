package com.example.transport_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import com.example.transport_service.entity.Parking;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ParkingResponse {
    private Long id;
    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private Integer totalSpots;
    private Integer availableSpots;
    private BigDecimal pricePerHour;

    public static ParkingResponse from(Parking p) {
        return new ParkingResponse(
                p.getId(), p.getName(), p.getAddress(),
                p.getLatitude(), p.getLongitude(),
                p.getTotalSpots(), p.getAvailableSpots(), p.getPricePerHour()
        );
    }
}