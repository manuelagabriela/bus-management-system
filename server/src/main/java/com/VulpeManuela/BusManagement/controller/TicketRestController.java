package com.VulpeManuela.BusManagement.controller;

import com.VulpeManuela.BusManagement.dto.ReservationDTO;
import com.VulpeManuela.BusManagement.dto.SeatDTO;
import com.VulpeManuela.BusManagement.dto.TicketDTO;
import com.VulpeManuela.BusManagement.entity.Ticket;
import com.VulpeManuela.BusManagement.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketRestController {
    private final TicketService ticketService;

    @Autowired
    public TicketRestController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody TicketDTO ticketDTO) {
        TicketDTO save = ticketService.save(ticketDTO);
        return ResponseEntity.ok(save);
    }

    @GetMapping("/list")
    public List<TicketDTO> list() {
        return ticketService.findAll();
    }

    @GetMapping("/findById")
    public TicketDTO findById(@RequestParam Long id) {
        return ticketService.findById(id);
    }

    @PostMapping("/find")
    public List<TicketDTO> find(@RequestBody ReservationDTO reservationDTO){
        return ticketService.find(reservationDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id){
        ticketService.delete(id);
    }

    @GetMapping("/price")
    public Double getPrice(@RequestBody TicketDTO ticketDTO){
        return ticketService.getPrice(ticketDTO);
    }
}
