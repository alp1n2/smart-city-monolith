package com.example.transport_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.transport_service.dto.ParkingResponse;
import com.example.transport_service.dto.ReservationResponse;
import com.example.transport_service.dto.ReserveRequest;
import com.example.transport_service.entity.Parking;
import com.example.transport_service.entity.ParkingReservation;
import com.example.transport_service.repository.ParkingRepository;
import com.example.transport_service.repository.ParkingReservationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingService {

    private final ParkingRepository parkingRepository;
    private final ParkingReservationRepository reservationRepository;

    public List<ParkingResponse> getAll() {
        return parkingRepository.findAll().stream()
                .map(ParkingResponse::from)
                .toList();
    }

    @Transactional
    public ReservationResponse reserve(Long parkingId, Long userId, ReserveRequest request) {
        Parking parking = parkingRepository.findById(parkingId)
                .orElseThrow(() -> new IllegalArgumentException("Парковка не найдена"));

        if (parking.getAvailableSpots() <= 0) {
            throw new IllegalArgumentException("Нет свободных мест");
        }

        if (request.getReservedTo().isBefore(request.getReservedFrom())) {
            throw new IllegalArgumentException("Дата окончания раньше даты начала");
        }

        parking.setAvailableSpots(parking.getAvailableSpots() - 1);
        parkingRepository.save(parking);

        ParkingReservation reservation = new ParkingReservation();
        reservation.setParkingId(parkingId);
        reservation.setUserId(userId);
        reservation.setVehicleNumber(request.getVehicleNumber());
        reservation.setReservedFrom(request.getReservedFrom());
        reservation.setReservedTo(request.getReservedTo());
        reservation.setStatus("ACTIVE");

        return ReservationResponse.from(reservationRepository.save(reservation));
    }
}