package com.VulpeManuela.BusManagement.controller;

import com.VulpeManuela.BusManagement.dto.ClientDTO;
import com.VulpeManuela.BusManagement.dto.UserDTO;
import com.VulpeManuela.BusManagement.service.ClientService;
import com.VulpeManuela.BusManagement.utils.Notification;
import com.VulpeManuela.BusManagement.validate.ClientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientRestController {
    private final ClientService clientService;

    @Autowired
    public ClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody ClientDTO clientDTO) {
        ClientValidator clientValidator = new ClientValidator(clientService);
        Notification notification = clientValidator.clientCheck(clientDTO);
        ClientDTO client;
        if(!notification.hasErrors()) {
            client = clientService.save(clientDTO);
            return ResponseEntity.ok(client);
        }
        return new ResponseEntity<Object>(notification, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/list")
    public List<ClientDTO> list() {
        return clientService.findAll();
    }

    @GetMapping("/findById/{id}")
    public ClientDTO findById(@PathVariable("id") Long id) {
        return clientService.findById(id);
    }

    @PostMapping("/find")
    public List<ClientDTO> find(@RequestBody ClientDTO clientDTO){
        return clientService.find(clientDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id){
        clientService.delete(id);
    }
}
