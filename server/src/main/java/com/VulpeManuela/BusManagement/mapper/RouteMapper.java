package com.VulpeManuela.BusManagement.mapper;

import com.VulpeManuela.BusManagement.dto.ClientDTO;
import com.VulpeManuela.BusManagement.dto.RouteDTO;
import com.VulpeManuela.BusManagement.entity.Client;
import com.VulpeManuela.BusManagement.entity.Route;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

@Mapper
public abstract class RouteMapper {
    @Autowired
    EntityManager entityManager;

    public abstract void toEntityUpdate(@MappingTarget Route route, RouteDTO routeDTO);

    public abstract RouteDTO toDTO(Route route);
}
