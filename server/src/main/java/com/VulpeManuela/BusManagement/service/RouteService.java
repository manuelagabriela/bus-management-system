package com.VulpeManuela.BusManagement.service;

import com.VulpeManuela.BusManagement.dto.BusDTO;
import com.VulpeManuela.BusManagement.dto.ClientDTO;
import com.VulpeManuela.BusManagement.dto.RouteDTO;

import java.util.List;

public interface RouteService {
    RouteDTO save(RouteDTO routeDTO);

    List<RouteDTO> findAll();

    RouteDTO findById(Long id);

    List<RouteDTO> find(RouteDTO routeDTO);

    void delete(Long id);

    List<BusDTO> findBuses(RouteDTO routeDTO);
}
