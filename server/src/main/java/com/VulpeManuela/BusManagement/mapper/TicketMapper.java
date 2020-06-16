package com.VulpeManuela.BusManagement.mapper;

import com.VulpeManuela.BusManagement.dto.ClientDTO;
import com.VulpeManuela.BusManagement.dto.TicketDTO;
import com.VulpeManuela.BusManagement.entity.Client;
import com.VulpeManuela.BusManagement.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

@Mapper
public abstract class TicketMapper {
    @Autowired
    EntityManager entityManager;

    public abstract void toEntityUpdate(@MappingTarget Ticket ticket, TicketDTO ticketDTO);

    public abstract TicketDTO toDTO(Ticket ticket);
}
