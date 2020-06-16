package ClientBusManagement.gateway.impl;

import ClientBusManagement.conf.RestProperties;
import ClientBusManagement.dto.BusDTO;
import ClientBusManagement.dto.DriverDTO;
import ClientBusManagement.dto.RouteDTO;
import ClientBusManagement.gateway.RouteGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class RouteGatewayImpl implements RouteGateway {
    private final String URL = "/route";

    private final RestProperties restProperties;

    @Autowired
    public RouteGatewayImpl(RestProperties restProperties) {
        this.restProperties = restProperties;
    }

    @Override
    public List<RouteDTO> findAll() {
        String url = restProperties.getUrl() + URL + "/list";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RouteDTO[]> forEntity = restTemplate.getForEntity(url, RouteDTO[].class);
        RouteDTO[] response = restTemplate.getForObject(url, RouteDTO[].class);
        return Arrays.asList(response);
    }

    @Override
    public RouteDTO save(RouteDTO routeDTO) {
        String url = restProperties.getUrl() + URL + "/save";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> httpEntity = new HttpEntity<>(routeDTO);
        RouteDTO response = restTemplate.postForObject(url, httpEntity, RouteDTO.class);
        return response;
    }

    @Override
    public void delete(Long id) {
        String url = restProperties.getUrl() + URL + "/delete/"+id;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(url, id);
    }

    @Override
    public List<BusDTO> findBuses(RouteDTO routeDTO) {
        String url = restProperties.getUrl() + URL + "/findBuses";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> httpEntity = new HttpEntity<>(routeDTO);
        BusDTO[] response = restTemplate.postForObject(url, httpEntity, BusDTO[].class);
        return Arrays.asList(response);
    }
}
