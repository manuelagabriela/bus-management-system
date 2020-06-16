package com.VulpeManuela.BusManagement.mapper;

import com.VulpeManuela.BusManagement.dto.ClientDTO;
import com.VulpeManuela.BusManagement.dto.ReservationDTO;
import com.VulpeManuela.BusManagement.entity.Client;
import com.VulpeManuela.BusManagement.entity.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

@Mapper
public abstract class ReservationMapper {
    @Autowired
    EntityManager entityManager;

    public abstract void toEntityUpdate(@MappingTarget Reservation reservation, ReservationDTO reservationDTO);

    public abstract ReservationDTO toDTO(Reservation reservation);
}
