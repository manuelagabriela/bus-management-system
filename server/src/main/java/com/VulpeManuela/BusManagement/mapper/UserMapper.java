package com.VulpeManuela.BusManagement.mapper;

import com.VulpeManuela.BusManagement.dto.NameIdDTO;
import com.VulpeManuela.BusManagement.dto.UserDTO;
import com.VulpeManuela.BusManagement.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

@Mapper
public abstract class UserMapper {

    @Autowired
    EntityManager entityManager;

    public abstract User toEntity(UserDTO userDTO);

    public abstract void toEntityUpdate(@MappingTarget  User user, UserDTO userDTO);

    public abstract UserDTO toDTO(User user);

    public User toEntity(NameIdDTO nameIdDTO){
        return entityManager.find(User.class, nameIdDTO.getId());
    }
}
