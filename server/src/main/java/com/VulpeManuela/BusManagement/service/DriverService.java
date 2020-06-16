package com.VulpeManuela.BusManagement.service;

import com.VulpeManuela.BusManagement.dto.ClientDTO;
import com.VulpeManuela.BusManagement.dto.DriverDTO;

import java.util.List;

public interface DriverService {
    DriverDTO save(DriverDTO driverDTO);

    List<DriverDTO> findAll();

    DriverDTO findById(Long id);

    List<DriverDTO> find(DriverDTO driverDTO);

    void delete(Long id);
}
