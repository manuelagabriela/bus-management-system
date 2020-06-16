package ClientBusManagement.gateway.impl;

import ClientBusManagement.conf.RestProperties;
import ClientBusManagement.dto.DriverDTO;
import ClientBusManagement.dto.UserDTO;
import ClientBusManagement.enumeration.UserRole;
import ClientBusManagement.gateway.DriverGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class DriverGatewayImpl implements DriverGateway {
    private final String URL = "/driver";

    private final RestProperties restProperties;

    @Autowired
    public DriverGatewayImpl(RestProperties restProperties) {
        this.restProperties = restProperties;
    }

    @Override
    public List<DriverDTO> findAll() {
        String url = restProperties.getUrl() + URL + "/list";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<DriverDTO[]> forEntity = restTemplate.getForEntity(url, DriverDTO[].class);
        DriverDTO[] response = restTemplate.getForObject(url, DriverDTO[].class);
       // System.out.println(response.length);
        return Arrays.asList(response);
    }

    @Override
    public DriverDTO save(DriverDTO driverDTO) {
        String url = restProperties.getUrl() + URL + "/save";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> httpEntity = new HttpEntity<>(driverDTO);
        DriverDTO response = restTemplate.postForObject(url, httpEntity, DriverDTO.class);
        return response;
    }

    @Override
    public void delete(Long id) {
        String url = restProperties.getUrl() + URL + "/delete/"+id;
        RestTemplate restTemplate = new RestTemplate();
        //System.out.println(id);
        restTemplate.delete(url, id);
    }
}
