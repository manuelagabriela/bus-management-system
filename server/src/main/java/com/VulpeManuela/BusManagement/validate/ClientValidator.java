package com.VulpeManuela.BusManagement.validate;

import com.VulpeManuela.BusManagement.dto.ClientDTO;
import com.VulpeManuela.BusManagement.dto.UserDTO;
import com.VulpeManuela.BusManagement.service.ClientService;
import com.VulpeManuela.BusManagement.utils.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientValidator {
    private final ClientService clientService;

    @Autowired
    public ClientValidator(ClientService clientService) {
        this.clientService = clientService;
    }

    public Notification clientCheck(ClientDTO clientDTO){
        Notification notification = new Notification();

        if(!clientDTO.getCNP().matches("[0-9]{13}") || !clientDTO.getPhone().matches("[0-9]{10}")){
            notification.addError("Invalid data!");
        }

        return notification;
    }
}
