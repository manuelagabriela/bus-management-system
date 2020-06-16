package com.VulpeManuela.BusManagement.controller;

import com.VulpeManuela.BusManagement.dto.ClientDTO;
import com.VulpeManuela.BusManagement.dto.ReservationDTO;
import com.VulpeManuela.BusManagement.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationRestController {
    private final ReservationService reservationService;

    @Autowired
    public ReservationRestController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody ReservationDTO reservationDTO) {
        ReservationDTO save = reservationService.save(reservationDTO);
        return ResponseEntity.ok(save);
    }

    @GetMapping("/list")
    public List<ReservationDTO> list() {
        return reservationService.findAll();
    }

    @GetMapping("/findById")
    public ReservationDTO findById(@RequestParam  Long id) {
        return reservationService.findById(id);
    }

    @GetMapping("/find")
    public List<ReservationDTO> find(@RequestParam Long id){
        return reservationService.find(id);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Long id){
        reservationService.delete(id);
    }

    @PostMapping("/totalPrice")
    public Double getTotalPrice(@RequestBody ReservationDTO reservationDTO){
        return reservationService.getTotalPrice(reservationDTO);
    }
}
