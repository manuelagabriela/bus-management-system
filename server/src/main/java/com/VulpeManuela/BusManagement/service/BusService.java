package com.VulpeManuela.BusManagement.service;

import com.VulpeManuela.BusManagement.dto.BusDTO;
import com.VulpeManuela.BusManagement.dto.ClientDTO;
import com.VulpeManuela.BusManagement.dto.SeatDTO;

import java.text.ParseException;
import java.util.List;

public interface BusService {
    BusDTO save(BusDTO busDto);

    List<BusDTO> findAll();

    BusDTO findById(Long id);

    List<BusDTO> find(BusDTO busDTO);

    void delete(Long id);

    List<SeatDTO> findSeats(BusDTO busDTO);

    //List<BusDTO> findByDate(String date) throws ParseException;
}
