package com.VulpeManuela.BusManagement.service;

import com.VulpeManuela.BusManagement.dto.UserDTO;
import com.VulpeManuela.BusManagement.entity.User;

import java.io.IOException;
import java.util.List;

public interface UserService {

    UserDTO save(UserDTO userDto);

    List<UserDTO> findAll();

    UserDTO findById(Long id);

    List<UserDTO> find(UserDTO userDTO);

    void delete(Long id);

    void getReport(String reportType) throws IOException;

    UserDTO login(UserDTO userDTO);

    String testService();
}