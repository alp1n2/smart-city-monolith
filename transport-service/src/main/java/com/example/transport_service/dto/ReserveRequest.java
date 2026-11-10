package com.example.transport_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReserveRequest {

    @NotBlank
    private String vehicleNumber;

    @NotNull
    private LocalDateTime reservedFrom;

    @NotNull
    private LocalDateTime reservedTo;
}