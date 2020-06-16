package com.VulpeManuela.BusManagement.controller;

import com.VulpeManuela.BusManagement.dto.DriverDTO;
import com.VulpeManuela.BusManagement.dto.RouteDTO;
import com.VulpeManuela.BusManagement.entity.Driver;
import com.VulpeManuela.BusManagement.service.DriverService;
import com.VulpeManuela.BusManagement.utils.Notification;
import com.VulpeManuela.BusManagement.validate.DriverValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/driver")
public class DriverRestController {
    private final DriverService driverService;

    @Autowired
    public DriverRestController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody DriverDTO driverDTO) {
        DriverValidator driverValidator = new DriverValidator(driverService);
        Notification notification = driverValidator.driverCheck(driverDTO);
        DriverDTO save;
        if(!notification.hasErrors()) {
            save = driverService.save(driverDTO);
            return ResponseEntity.ok(save);
        }
        return new ResponseEntity<Object>(notification, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/list")
    public List<DriverDTO> list() {
        return driverService.findAll();
    }

    @GetMapping("/findById")
    public DriverDTO findById(@RequestParam Long id) {
        return driverService.findById(id);
    }

    @GetMapping("/find")
    public List<DriverDTO> find(@RequestBody DriverDTO driverDTO){
        return driverService.find(driverDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id){
        driverService.delete(id);
    }
}
