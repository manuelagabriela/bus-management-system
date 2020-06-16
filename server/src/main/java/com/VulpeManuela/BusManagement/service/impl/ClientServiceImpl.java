package com.VulpeManuela.BusManagement.service.impl;

import com.VulpeManuela.BusManagement.dto.ClientDTO;
import com.VulpeManuela.BusManagement.entity.Client;
import com.VulpeManuela.BusManagement.entity.User;
import com.VulpeManuela.BusManagement.mapper.ClientMapper;
import com.VulpeManuela.BusManagement.repository.ClientRepository;
import com.VulpeManuela.BusManagement.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientMapper clientMapper;
    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientMapper clientMapper, ClientRepository clientRepository) {
        this.clientMapper = clientMapper;
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDTO save(ClientDTO clientDto) {
        Client client;
        if(clientDto.getId() != null) {
            client = clientRepository.findById(clientDto.getId()).get();
        } else {
            client = new Client();
        }

        clientMapper.toEntityUpdate(client, clientDto);
        return clientMapper.toDTO(clientRepository.save(client));
    }

    @Override
    public List<ClientDTO> findAll() {
        return clientRepository.findAll()
                .stream()
                .map(clientMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO findById(Long id) {
        return clientMapper.toDTO(clientRepository.findById(id).get());
    }

    @Override
    public List<ClientDTO> find(ClientDTO clientDTO) {
        System.out.println(clientDTO.getUser().getId());
        return clientRepository.findAll()
                .stream()
                .filter(x -> StringUtils.isEmpty(clientDTO.getFirstName()) || x.getFirstName().contains(clientDTO.getFirstName()))
                .filter(x -> StringUtils.isEmpty(clientDTO.getLastName()) || x.getLastName().contains(clientDTO.getLastName()))
                .filter(x -> Objects.isNull(clientDTO.getId()) || Objects.equals(clientDTO.getId(), x.getId()))
                .filter(x -> StringUtils.isEmpty(clientDTO.getCNP()) || x.getCNP().contains(clientDTO.getCNP()))
                .filter(x -> Objects.isNull(clientDTO.getUser().getId()) || Objects.equals(clientDTO.getUser().getId(), x.getUser().getId()))
                .map(clientMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id){
        clientRepository.deleteById(id);
    }
}
