package com.VulpeManuela.BusManagement.mapper;

import com.VulpeManuela.BusManagement.dto.BusDTO;
import com.VulpeManuela.BusManagement.dto.ClientDTO;
import com.VulpeManuela.BusManagement.entity.Bus;
import com.VulpeManuela.BusManagement.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

@Mapper
public abstract class BusMapper {
    @Autowired
    EntityManager entityManager;

    public abstract void toEntityUpdate(@MappingTarget Bus bus, BusDTO busDTO);

    public abstract BusDTO toDTO(Bus bus);
}
