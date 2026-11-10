package com.example.transport_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import com.example.transport_service.entity.Vehicle;

@Getter
@AllArgsConstructor
public class VehicleResponse {
    private Long id;
    private String type;
    private String number;
    private String model;
    private Double latitude;
    private Double longitude;
    private String status;

    public static VehicleResponse from(Vehicle v) {
        return new VehicleResponse(
                v.getId(), v.getType(), v.getNumber(), v.getModel(),
                v.getLatitude(), v.getLongitude(), v.getStatus()
        );
    }
}