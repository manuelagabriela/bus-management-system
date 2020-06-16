package com.VulpeManuela.BusManagement.service;

import com.VulpeManuela.BusManagement.dto.ClientDTO;
import com.VulpeManuela.BusManagement.dto.ReservationDTO;
import com.VulpeManuela.BusManagement.entity.Reservation;

import java.util.List;

public interface ReservationService {
    ReservationDTO save(ReservationDTO reservationDTO);

    List<ReservationDTO> findAll();

    ReservationDTO findById(Long id);

    List<ReservationDTO> find(Long id);

    void delete(Long id);

    Double getTotalPrice(ReservationDTO reservationDTO);
}
