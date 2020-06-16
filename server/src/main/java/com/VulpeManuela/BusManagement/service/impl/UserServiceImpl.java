package com.VulpeManuela.BusManagement.service.impl;

import com.VulpeManuela.BusManagement.dto.ReservationDTO;
import com.VulpeManuela.BusManagement.dto.UserDTO;
import com.VulpeManuela.BusManagement.entity.User;
import com.VulpeManuela.BusManagement.factory.impl.ReportFactory;
import com.VulpeManuela.BusManagement.mapper.UserMapper;
import com.VulpeManuela.BusManagement.repository.ReservationRepository;
import com.VulpeManuela.BusManagement.repository.UserRepository;
import com.VulpeManuela.BusManagement.service.ReservationService;
import com.VulpeManuela.BusManagement.service.UserService;
import com.VulpeManuela.BusManagement.validate.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final ReservationService reservationService;

    @Autowired
    public UserServiceImpl(UserMapper userMapper,
                           UserRepository userRepository,
                           ReservationService reservationService) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.reservationService = reservationService;
    }

    @Override
    public UserDTO save(UserDTO userDto) {
        User user;
        if(userDto.getId() != null) {
            user = userRepository.findById(userDto.getId()).get();
        } else {
            user = new User();
        }

        userMapper.toEntityUpdate(user, userDto);
        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(Long id) {
        return userMapper.toDTO(userRepository.findById(id).get());
    }

    @Override
    public List<UserDTO> find(UserDTO userDTO){
        return userRepository.findAll()
                .stream()
                .filter(x -> StringUtils.isEmpty(userDTO.getUsername()) || x.getUsername().contains(userDTO.getUsername()))
                .filter(x -> Objects.isNull(userDTO.getId()) || Objects.equals(userDTO.getId(), x.getId()))
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id){
        userRepository.deleteById(id);
    }

    @Override
    public void getReport(String reportType) throws IOException {
        List<ReservationDTO> reservationDTOList = reservationService.findAll()
                .stream()
                .filter(x -> x.getClient().getNoOfReservations() == 5)
                .collect(Collectors.toList());

        ReportFactory reportFactory = new ReportFactory();
        reportFactory.getReport(reportType).genReport(reservationDTOList);
    }

    @Override
    public UserDTO login(UserDTO userDTO){
        List<UserDTO> userDTOList = userRepository.findAll()
                .stream()
                .filter(x -> x.getUsername().equals(userDTO.getUsername()) && x.getPassword().equals(userDTO.getPassword()))
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
        if(userDTOList.size() == 0)
            return null;
        else
            return userDTOList.get(0);
    }

    @Override
    public String testService() {
        return "service working";
    }
}
