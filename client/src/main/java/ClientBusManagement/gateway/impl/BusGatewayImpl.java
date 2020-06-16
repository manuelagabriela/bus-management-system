package ClientBusManagement.gateway.impl;

import ClientBusManagement.conf.RestProperties;
import ClientBusManagement.dto.BusDTO;
import ClientBusManagement.dto.RouteDTO;
import ClientBusManagement.gateway.BusGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class BusGatewayImpl implements BusGateway {
    private final RestProperties restProperties;
    private final String URL = "/bus";

    @Autowired
    public BusGatewayImpl(RestProperties restProperties) {
        this.restProperties = restProperties;
    }

    @Override
    public List<BusDTO> findAll() {
        String url = restProperties.getUrl() + URL + "/list";
        RestTemplate restTemplate = new RestTemplate();
        BusDTO[] response = restTemplate.getForObject(url, BusDTO[].class);
        // System.out.println(response.length);
        return Arrays.asList(response);
    }

    @Override
    public BusDTO save(BusDTO busDTO) {
        String url = restProperties.getUrl() + URL + "/save";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> httpEntity = new HttpEntity<>(busDTO);
        BusDTO response = restTemplate.postForObject(url, httpEntity, BusDTO.class);
        return response;
    }

    @Override
    public void delete(Long id) {
        String url = restProperties.getUrl() + URL + "/delete/"+id;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(url, id);
    }

    @Override
    public List<BusDTO> find(BusDTO busDTO) {
        String url = restProperties.getUrl() + URL + "/find";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> httpEntity = new HttpEntity<>(busDTO);
        BusDTO[] response = restTemplate.postForObject(url, httpEntity, BusDTO[].class);
        return Arrays.asList(response);
    }
}
