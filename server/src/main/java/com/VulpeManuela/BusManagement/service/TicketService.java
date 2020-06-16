package com.VulpeManuela.BusManagement.service;

import com.VulpeManuela.BusManagement.dto.ClientDTO;
import com.VulpeManuela.BusManagement.dto.ReservationDTO;
import com.VulpeManuela.BusManagement.dto.TicketDTO;
import com.VulpeManuela.BusManagement.entity.Ticket;

import java.util.List;
import java.util.TooManyListenersException;

public interface TicketService {
    TicketDTO save(TicketDTO ticketDTO);

    List<TicketDTO> findAll();

    TicketDTO findById(Long id);

    List<TicketDTO> find(ReservationDTO reservationDTO);

    void delete(Long id);

    Double getPrice(TicketDTO ticketDTO);
}
