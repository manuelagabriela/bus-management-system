package com.VulpeManuela.BusManagement.service;

import com.VulpeManuela.BusManagement.dto.ClientDTO;
import com.VulpeManuela.BusManagement.dto.UserDTO;

import java.util.List;

public interface ClientService {
    ClientDTO save(ClientDTO clientDto);

    List<ClientDTO> findAll();

    ClientDTO findById(Long id);

    List<ClientDTO> find(ClientDTO clientDTO);

    void delete(Long id);
}
