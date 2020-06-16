package com.VulpeManuela.BusManagement.service.impl;

import com.VulpeManuela.BusManagement.dto.SeatDTO;
import com.VulpeManuela.BusManagement.entity.Bus;
import com.VulpeManuela.BusManagement.entity.Seat;
import com.VulpeManuela.BusManagement.mapper.SeatMapper;
import com.VulpeManuela.BusManagement.repository.SeatRepository;
import com.VulpeManuela.BusManagement.service.SeatService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SeatServiceImpl implements SeatService {
    public final SeatMapper seatMapper;
    public final SeatRepository seatRepository;

    public SeatServiceImpl(SeatMapper seatMapper, SeatRepository seatRepository) {
        this.seatMapper = seatMapper;
        this.seatRepository = seatRepository;
    }


    @Override
    public SeatDTO save(SeatDTO seatDTO) {
        Seat seat;
        if(seatDTO.getId() != null) {
            seat = seatRepository.findById(seatDTO.getId()).get();
        } else {
            seat = new Seat();
        }

        seatMapper.toEntityUpdate(seat, seatDTO);
        return seatMapper.toDTO(seatRepository.save(seat));
    }

    @Override
    public List<SeatDTO> findAll() {
        return seatRepository.findAll()
                .stream()
                .map(seatMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SeatDTO findById(Long id) {
        return seatMapper.toDTO(seatRepository.findById(id).get());
    }

    @Override
    public List<SeatDTO> find(SeatDTO seatDTO) {
        return seatRepository.findAll()
                .stream()
                .filter(x -> Objects.isNull(seatDTO.getNumber()) || Objects.equals(seatDTO.getNumber(), x.getNumber()))
                .filter(x -> Objects.isNull(seatDTO.getAvailable()) || Objects.equals(seatDTO.getAvailable(), x.getAvailable()))
                .filter(x -> Objects.isNull(seatDTO.getBus().getId()) || Objects.equals(seatDTO.getBus().getId(), x.getBus().getId()))
                .map(seatMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        seatRepository.deleteById(id);
    }
}
