package com.VulpeManuela.BusManagement.controller;

import com.VulpeManuela.BusManagement.dto.UserDTO;
import com.VulpeManuela.BusManagement.entity.User;
import com.VulpeManuela.BusManagement.service.UserService;
import com.VulpeManuela.BusManagement.utils.Notification;
import com.VulpeManuela.BusManagement.validate.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestController {
    private final UserService userService;
    private final UserValidator userValidator;

    @Autowired
    public UserRestController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping("/list")
    public List<UserDTO> list() {
        return userService.findAll();
    }

    @GetMapping("/findById/{id}")
    public UserDTO findById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody UserDTO userDTO) {
        UserDTO user = new UserDTO();
        UserDTO userValid = new UserDTO();
        userValid.setUsername(userDTO.getUsername());
        userValid.setPassword(userDTO.getPassword());
        UserValidator userValidator = new UserValidator(userService);
        Notification notification = userValidator.userCheck(userValid);
        //System.out.println(notification.hasErrors());
        if(!notification.hasErrors()) {
            user = userService.save(userDTO);
            return ResponseEntity.ok(user);
        }
        return new ResponseEntity<Object>(notification, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/find")
    public List<UserDTO> find(@RequestBody UserDTO userDTO){
        return userService.find(userDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id){
        userService.delete(id);
    }

    @GetMapping("/raport")
    public void getReport(@RequestParam String str) throws IOException {
        userService.getReport(str);
    }
    @PostMapping("/login")
    public UserDTO login(@RequestBody UserDTO userDTO){
        return userService.login(userDTO);
    }

    @GetMapping("/test")
    String testUser(){
        return userService.testService() + " and controller";
    }

}
