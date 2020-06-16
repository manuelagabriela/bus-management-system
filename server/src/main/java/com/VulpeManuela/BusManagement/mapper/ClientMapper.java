package com.VulpeManuela.BusManagement.mapper;

import com.VulpeManuela.BusManagement.dto.ClientDTO;
import com.VulpeManuela.BusManagement.dto.NameIdDTO;
import com.VulpeManuela.BusManagement.dto.UserDTO;
import com.VulpeManuela.BusManagement.entity.Client;
import com.VulpeManuela.BusManagement.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

@Mapper
public abstract class ClientMapper {
    @Autowired
    EntityManager entityManager;

    public abstract Client toEntity(ClientDTO clientDTO);

    public abstract void toEntityUpdate(@MappingTarget Client client, ClientDTO clientDTO);

    public abstract ClientDTO toDTO(Client client);
}
