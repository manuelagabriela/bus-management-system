package com.VulpeManuela.BusManagement.mapper;

import com.VulpeManuela.BusManagement.dto.ClientDTO;
import com.VulpeManuela.BusManagement.dto.SeatDTO;
import com.VulpeManuela.BusManagement.entity.Client;
import com.VulpeManuela.BusManagement.entity.Seat;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

@Mapper
public abstract class SeatMapper {
    @Autowired
    EntityManager entityManager;

    public abstract void toEntityUpdate(@MappingTarget Seat seat, SeatDTO seatDTO);

    public abstract SeatDTO toDTO(Seat seat);
}
