package com.VulpeManuela.BusManagement.controller;

import com.VulpeManuela.BusManagement.dto.BusDTO;
import com.VulpeManuela.BusManagement.dto.ReservationDTO;
import com.VulpeManuela.BusManagement.dto.RouteDTO;
import com.VulpeManuela.BusManagement.entity.Route;
import com.VulpeManuela.BusManagement.service.RouteService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/route")
public class RouteRestController {
    private final RouteService routeService;

    @Autowired
    public RouteRestController(RouteService routeService) {
        this.routeService = routeService;
    }

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody RouteDTO routeDTO) {
        RouteDTO save = routeService.save(routeDTO);
        return ResponseEntity.ok(save);
    }

    @GetMapping("/list")
    public List<RouteDTO> list() {
        return routeService.findAll();
    }

    @GetMapping("/findById")
    public RouteDTO findById(@RequestParam Long id) {
        return routeService.findById(id);
    }

    @GetMapping("/find")
    public List<RouteDTO> find(@RequestBody RouteDTO routeDTO){
        return routeService.find(routeDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id){
        routeService.delete(id);
    }

    @PostMapping("/findBuses")
    public List<BusDTO> findBuses(@RequestBody RouteDTO routeDTO){
        return routeService.findBuses(routeDTO);
    }
}
