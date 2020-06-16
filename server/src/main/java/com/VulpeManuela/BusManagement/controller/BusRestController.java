package com.VulpeManuela.BusManagement.controller;

import com.VulpeManuela.BusManagement.dto.BusDTO;
import com.VulpeManuela.BusManagement.dto.RouteDTO;
import com.VulpeManuela.BusManagement.dto.SeatDTO;
import com.VulpeManuela.BusManagement.entity.Bus;
import com.VulpeManuela.BusManagement.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/bus")
public class BusRestController {
    private final BusService busService;

    @Autowired
    public BusRestController(BusService busService) {
        this.busService = busService;
    }

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody BusDTO busDTO) {
        BusDTO save = busService.save(busDTO);
        return ResponseEntity.ok(save);
    }

    @GetMapping("/list")
    public List<BusDTO> list() {
        return busService.findAll();
    }

    @GetMapping("/findById")
    public BusDTO findById(@RequestParam Long id) {
        return busService.findById(id);
    }

    @PostMapping("/find")
    public List<BusDTO> find(@RequestBody BusDTO busDTO){
        return busService.find(busDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id){
        busService.delete(id);
    }

    @GetMapping("/findSeats")
    public List<SeatDTO> findSeats(@RequestBody BusDTO busDTO){
        return busService.findSeats(busDTO);
    }

//    @GetMapping("/findByDate")
//    public List<BusDTO> findByDate(@RequestBody String date) throws ParseException {
//        System.out.println("aaaa");
//        return busService.findByDate(date);
//    }
}
