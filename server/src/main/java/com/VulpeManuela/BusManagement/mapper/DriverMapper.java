package com.VulpeManuela.BusManagement.mapper;

import com.VulpeManuela.BusManagement.dto.ClientDTO;
import com.VulpeManuela.BusManagement.dto.DriverDTO;
import com.VulpeManuela.BusManagement.entity.Client;
import com.VulpeManuela.BusManagement.entity.Driver;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

@Mapper
public abstract class DriverMapper {
    @Autowired
    EntityManager entityManager;

    public abstract void toEntityUpdate(@MappingTarget Driver driver, DriverDTO driverDTO);

    public abstract DriverDTO toDTO(Driver driver);
}
