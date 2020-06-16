package com.VulpeManuela.BusManagement.mapper;

import com.VulpeManuela.BusManagement.dto.NameIdDTO;
import com.VulpeManuela.BusManagement.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface NameIdMapper {
    @Mapping(target = "name", source = "username")
    NameIdDTO userToNameIdDTO(User user);
}
