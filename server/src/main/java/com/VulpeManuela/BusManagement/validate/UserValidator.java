package com.VulpeManuela.BusManagement.validate;

import com.VulpeManuela.BusManagement.dto.UserDTO;
import com.VulpeManuela.BusManagement.entity.User;
import com.VulpeManuela.BusManagement.repository.UserRepository;
import com.VulpeManuela.BusManagement.service.UserService;
import com.VulpeManuela.BusManagement.utils.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserValidator {
    private final UserService userService;

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    public Notification userCheck(UserDTO userDTO){
        Notification notification = new Notification();
        UserDTO user = null;
        if(userService.find(userDTO).size() != 0) {
            user = userService.find(userDTO).get(0);
        }
        if(!userDTO.getUsername().matches("^[a-zA-Z0-9_ ]+$")){
            notification.addError("Username invalid!");
        }

        if(user != null){
            notification.addError("Username already exists!");
        }

        return notification;
    }
}
