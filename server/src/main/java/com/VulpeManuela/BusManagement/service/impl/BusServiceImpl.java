package com.VulpeManuela.BusManagement.service.impl;

import com.VulpeManuela.BusManagement.dto.BusDTO;
import com.VulpeManuela.BusManagement.dto.SeatDTO;
import com.VulpeManuela.BusManagement.entity.Bus;
import com.VulpeManuela.BusManagement.mapper.BusMapper;
import com.VulpeManuela.BusManagement.mapper.SeatMapper;
import com.VulpeManuela.BusManagement.repository.BusRepository;
import com.VulpeManuela.BusManagement.service.BusService;
import com.VulpeManuela.BusManagement.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BusServiceImpl implements BusService {
    private final BusMapper busMapper;
    private final BusRepository busRepository;
    private final SeatService seatService;

    @Autowired
    public BusServiceImpl(BusMapper busMapper, BusRepository busRepository, SeatService seatService) {
        this.busMapper = busMapper;
        this.busRepository = busRepository;
        this.seatService = seatService;
    }

    @Override
    public BusDTO save(BusDTO busDto) {
        Bus bus;
        if(busDto.getId() != null) {
            bus = busRepository.findById(busDto.getId()).get();
        } else {
            bus = new Bus();
        }

        busMapper.toEntityUpdate(bus, busDto);
        return busMapper.toDTO(busRepository.save(bus));
    }

    @Override
    public List<BusDTO> findAll() {
        return busRepository.findAll()
                .stream()
                .map(busMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BusDTO findById(Long id) {
        return busMapper.toDTO(busRepository.findById(id).get());
    }

    @Override
    public List<BusDTO> find(BusDTO busDTO) {
        System.out.println(busDTO.getDate());
        return busRepository.findAll()
                .stream()
                .filter(x -> StringUtils.isEmpty(busDTO.getFirm()) || x.getFirm().contains(busDTO.getFirm()))
                .filter(x -> Objects.isNull(busDTO.getRoute().getId()) || Objects.equals(busDTO.getRoute().getId(), x.getRoute().getId()))
                .filter(x -> Objects.isNull(busDTO.getDriver().getId()) || Objects.equals(busDTO.getDriver().getId(), x.getDriver().getId()))
                .filter(x -> StringUtils.isEmpty(busDTO.getDate()) || busDTO.getDate().toString().equals(x.getDate().toString()))
                .map(busMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SeatDTO> findSeats(BusDTO busDTO){
        return seatService.findAll()
                .stream()
                .filter(x -> Objects.isNull(busDTO.getId()) || Objects.equals(busDTO.getId(), x.getBus().getId()))
                .filter(x -> StringUtils.isEmpty(busDTO.getDate()) || busDTO.getDate().toString().equals(x.getBus().getDate().toString()))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        busRepository.deleteById(id);
    }
}
