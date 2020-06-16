package com.VulpeManuela.BusManagement.controller;

import com.VulpeManuela.BusManagement.dto.BusDTO;
import com.VulpeManuela.BusManagement.dto.SeatDTO;
import com.VulpeManuela.BusManagement.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seat")
public class SeatRestController {
    private final SeatService seatService;

    @Autowired
    public SeatRestController(SeatService seatService) {
        this.seatService = seatService;
    }

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody SeatDTO seatDTO) {
        SeatDTO save = seatService.save(seatDTO);
        return ResponseEntity.ok(save);
    }

    @GetMapping("/list")
    public List<SeatDTO> list() {
        return seatService.findAll();
    }

    @GetMapping("/findById")
    public SeatDTO findById(@RequestParam Long id) {
        return seatService.findById(id);
    }

    @PostMapping("/find")
    public List<SeatDTO> find(@RequestBody SeatDTO seatDTO){
        return seatService.find(seatDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id){
        seatService.delete(id);
    }
}
