package com.VulpeManuela.BusManagement.service.impl;

import com.VulpeManuela.BusManagement.dto.ClientDTO;
import com.VulpeManuela.BusManagement.dto.ReservationDTO;
import com.VulpeManuela.BusManagement.dto.TicketDTO;
import com.VulpeManuela.BusManagement.entity.Reservation;
import com.VulpeManuela.BusManagement.mapper.ReservationMapper;
import com.VulpeManuela.BusManagement.repository.ReservationRepository;
import com.VulpeManuela.BusManagement.service.ClientService;
import com.VulpeManuela.BusManagement.service.ReservationService;
import com.VulpeManuela.BusManagement.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final TicketService ticketService;
    private final ClientService clientService;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, ReservationMapper reservationMapper, TicketService ticketService, ClientService clientService) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
        this.ticketService = ticketService;
        this.clientService = clientService;
    }

    @Override
    public ReservationDTO save(ReservationDTO reservationDTO) {
        Reservation reservation;
        if(reservationDTO.getId() != null) {
            reservation = reservationRepository.findById(reservationDTO.getId()).get();
        } else {
            reservation = new Reservation();
        }

        reservationMapper.toEntityUpdate(reservation, reservationDTO);
        return reservationMapper.toDTO(reservationRepository.save(reservation));
    }

    @Override
    public List<ReservationDTO> findAll() {
        return reservationRepository.findAll()
                .stream()
                .map(reservationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReservationDTO findById(Long id) {
        return reservationMapper.toDTO(reservationRepository.findById(id).get());
    }

    @Override
    public List<ReservationDTO> find(Long id) {
        return reservationRepository.findAll()
                .stream()
                .filter(x -> Objects.isNull(id) || Objects.equals(id, x.getClient().getId()))
                .map(reservationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        ClientDTO client = clientService.findById(findById(id).getClient().getId());
        client.setId(findById(id).getClient().getId());
        client.setNoOfReservations(client.getNoOfReservations() - 1);
        clientService.save(client);
        reservationRepository.deleteById(id);
    }

    @Override
    public Double getTotalPrice(ReservationDTO reservationDTO){
        List<TicketDTO> ticketDTOList = ticketService.find(reservationDTO);
        Double price = 0.0;

        ClientDTO clientDTO = clientService.findById(reservationDTO.getClient().getId());

        for(TicketDTO it: ticketDTOList){
            price += ticketService.getPrice(it);
            System.out.println(price + " ");
        }

        if(clientDTO.getNoOfReservations() < 5) {
            clientDTO.setNoOfReservations(clientDTO.getNoOfReservations() + 1);
            clientService.save(clientDTO);
            return price;
        }else{
            clientDTO.setNoOfReservations(0L);
            clientService.save(clientDTO);
            return price - price * 0.25;
        }
    }
}
