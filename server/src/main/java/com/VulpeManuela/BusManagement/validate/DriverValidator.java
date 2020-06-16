package com.VulpeManuela.BusManagement.validate;

import com.VulpeManuela.BusManagement.dto.ClientDTO;
import com.VulpeManuela.BusManagement.dto.DriverDTO;
import com.VulpeManuela.BusManagement.service.DriverService;
import com.VulpeManuela.BusManagement.utils.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DriverValidator {
    private final DriverService driverService;

    @Autowired
    public DriverValidator(DriverService driverService) {
        this.driverService = driverService;
    }

    public Notification driverCheck(DriverDTO driverDTO){
        Notification notification = new Notification();

        if(!driverDTO.getCNP().matches("[0-9]{13}") || !driverDTO.getPhone().matches("[0-9]{10}")){
            notification.addError("Invalid data!");
        }

        return notification;
    }
}
