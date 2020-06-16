package com.VulpeManuela.BusManagement.service.impl;

import com.VulpeManuela.BusManagement.dto.BusDTO;
import com.VulpeManuela.BusManagement.dto.RouteDTO;
import com.VulpeManuela.BusManagement.entity.Route;
import com.VulpeManuela.BusManagement.mapper.BusMapper;
import com.VulpeManuela.BusManagement.mapper.RouteMapper;
import com.VulpeManuela.BusManagement.repository.BusRepository;
import com.VulpeManuela.BusManagement.repository.RouteRepository;
import com.VulpeManuela.BusManagement.service.RouteService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteMapper routeMapper;
    private final RouteRepository routeRepository;
    private final BusRepository busService;
    private final BusMapper busMapper;

    public RouteServiceImpl(RouteMapper routeMapper, RouteRepository routeRepository, BusRepository busService, BusMapper busMapper) {
        this.routeMapper = routeMapper;
        this.routeRepository = routeRepository;
        this.busService = busService;
        this.busMapper = busMapper;
    }

    @Override
    public RouteDTO save(RouteDTO routeDTO) {
        Route route;
        if(routeDTO.getId() != null) {
            route = routeRepository.findById(routeDTO.getId()).get();
        } else {
            route = new Route();
        }

        routeMapper.toEntityUpdate(route, routeDTO);
        return routeMapper.toDTO(routeRepository.save(route));
    }

    @Override
    public List<RouteDTO> findAll() {
        return routeRepository.findAll()
                .stream()
                .map(routeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RouteDTO findById(Long id) {
        return routeMapper.toDTO(routeRepository.findById(id).get());
    }

    @Override
    public List<RouteDTO> find(RouteDTO routeDTO) {
        return routeRepository.findAll()
                .stream()
                .filter(x -> StringUtils.isEmpty(routeDTO.getSource()) || x.getSource().contains(routeDTO.getSource()))
                .filter(x -> StringUtils.isEmpty(routeDTO.getDestination()) || x.getDestination().contains(routeDTO.getDestination()))
                .map(routeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BusDTO> findBuses(RouteDTO routeDTO){
        return busService.findAll()
                .stream()
                .filter(x -> Objects.isNull(find(routeDTO).get(0).getId()) || Objects.equals(find(routeDTO).get(0).getId(), x.getRoute().getId()))
                .map(busMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        routeRepository.deleteById(id);
    }
}
