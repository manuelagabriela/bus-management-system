package com.VulpeManuela.BusManagement.service;

import com.VulpeManuela.BusManagement.dto.ClientDTO;
import com.VulpeManuela.BusManagement.dto.SeatDTO;

import java.util.List;

public interface SeatService {
    SeatDTO save(SeatDTO seatDTO);

    List<SeatDTO> findAll();

    SeatDTO findById(Long id);

    List<SeatDTO> find(SeatDTO seatDTO);

    void delete(Long id);
}
