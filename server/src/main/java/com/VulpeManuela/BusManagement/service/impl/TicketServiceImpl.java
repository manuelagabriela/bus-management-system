package com.VulpeManuela.BusManagement.service.impl;

import com.VulpeManuela.BusManagement.dto.ReservationDTO;
import com.VulpeManuela.BusManagement.dto.SeatDTO;
import com.VulpeManuela.BusManagement.dto.TicketDTO;
import com.VulpeManuela.BusManagement.entity.Ticket;
import com.VulpeManuela.BusManagement.mapper.TicketMapper;
import com.VulpeManuela.BusManagement.repository.TicketRepository;
import com.VulpeManuela.BusManagement.service.SeatService;
import com.VulpeManuela.BusManagement.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketMapper ticketMapper;
    private final TicketRepository ticketRepository;
    private final SeatService seatService;

    @Autowired
    public TicketServiceImpl(TicketMapper ticketMapper, TicketRepository ticketRepository, SeatService seatService) {
        this.ticketMapper = ticketMapper;
        this.ticketRepository = ticketRepository;
        this.seatService = seatService;
    }


    @Override
    public TicketDTO save(TicketDTO ticketDTO) {
        Ticket ticket;
        if(ticketDTO.getId() != null) {
            ticket = ticketRepository.findById(ticketDTO.getId()).get();
        } else {
            ticket = new Ticket();
        }

        SeatDTO seatDTO = seatService.findById(ticketDTO.getSeat().getId());
        seatDTO.setId(ticketDTO.getSeat().getId());
        seatDTO.setAvailable(false);
        seatService.save(seatDTO);
        ticketMapper.toEntityUpdate(ticket, ticketDTO);
        return ticketMapper.toDTO(ticketRepository.save(ticket));
    }

    @Override
    public List<TicketDTO> findAll() {
        return ticketRepository.findAll()
                .stream()
                .map(ticketMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TicketDTO findById(Long id) {
        return ticketMapper.toDTO(ticketRepository.findById(id).get());
    }

    @Override
    public List<TicketDTO> find(ReservationDTO reservationDTO) {
        return ticketRepository.findAll()
                .stream()
                .filter(x -> Objects.isNull(reservationDTO.getId()) || Objects.equals(reservationDTO.getId(), x.getReservation().getId()))
                .map(ticketMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        SeatDTO seatDTO = seatService.findById(ticketRepository.findById(id).get().getSeat().getId());
        seatDTO.setId(ticketRepository.findById(id).get().getSeat().getId());
        seatDTO.setAvailable(true);
        seatService.save(seatDTO);
        ticketRepository.deleteById(id);
    }

    @Override
    public Double getPrice(TicketDTO ticketDTO){
        return ticketRepository.findAll()
                .stream()
                .filter(x -> Objects.isNull(ticketDTO.getSeat().getId()) || Objects.equals(ticketDTO.getSeat().getId(), x.getSeat().getId()))
                .map(ticketMapper::toDTO)
                .collect(Collectors.toList())
                .get(0).getSeat().getBus().getRoute().getPrice();
    }
}
