package com.VulpeManuela.BusManagement.service.impl;

import com.VulpeManuela.BusManagement.dto.DriverDTO;
import com.VulpeManuela.BusManagement.entity.Driver;
import com.VulpeManuela.BusManagement.entity.Route;
import com.VulpeManuela.BusManagement.mapper.DriverMapper;
import com.VulpeManuela.BusManagement.repository.DriverRepository;
import com.VulpeManuela.BusManagement.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DriverServiceImpl implements DriverService {
    private final DriverMapper driverMapper;
    private final DriverRepository driverRepository;

    @Autowired
    public DriverServiceImpl(DriverMapper driverMapper, DriverRepository driverRepository) {
        this.driverMapper = driverMapper;
        this.driverRepository = driverRepository;
    }


    @Override
    public DriverDTO save(DriverDTO driverDTO) {
        Driver driver;
        if(driverDTO.getId() != null) {
            driver = driverRepository.findById(driverDTO.getId()).get();
        } else {
            driver = new Driver();
        }

        driverMapper.toEntityUpdate(driver, driverDTO);
        return driverMapper.toDTO(driverRepository.save(driver));
    }

    @Override
    public List<DriverDTO> findAll() {
        return driverRepository.findAll()
                .stream()
                .map(driverMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DriverDTO findById(Long id) {
        return driverMapper.toDTO(driverRepository.findById(id).get());
    }

    @Override
    public List<DriverDTO> find(DriverDTO driverDTO) {
        return driverRepository.findAll()
                .stream()
                .filter(x -> StringUtils.isEmpty(driverDTO.getFirstName()) || x.getFirstName().contains(driverDTO.getFirstName()))
                .filter(x -> StringUtils.isEmpty(driverDTO.getLastName()) || x.getLastName().contains(driverDTO.getLastName()))
                .filter(x -> Objects.isNull(driverDTO.getId()) || Objects.equals(driverDTO.getId(), x.getId()))
                .map(driverMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        driverRepository.deleteById(id);
    }
}
