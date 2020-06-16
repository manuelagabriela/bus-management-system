package ClientBusManagement.gateway.impl;

import ClientBusManagement.conf.RestProperties;
import ClientBusManagement.dto.BusDTO;
import ClientBusManagement.dto.SeatDTO;
import ClientBusManagement.gateway.SeatGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class SeatGatewayImpl implements SeatGateway {
    private final RestProperties restProperties;
    private final String URL = "/seat";

    @Autowired
    public SeatGatewayImpl(RestProperties restProperties) {
        this.restProperties = restProperties;
    }

    @Override
    public List<SeatDTO> findAll() {
        String url = restProperties.getUrl() + URL + "/list";
        RestTemplate restTemplate = new RestTemplate();
        SeatDTO[] response = restTemplate.getForObject(url, SeatDTO[].class);
        // System.out.println(response.length);
        return Arrays.asList(response);
    }

    @Override
    public SeatDTO save(SeatDTO seatDTO) {
        String url = restProperties.getUrl() + URL + "/save";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> httpEntity = new HttpEntity<>(seatDTO);
        SeatDTO response = restTemplate.postForObject(url, httpEntity, SeatDTO.class);
        return response;
    }

    @Override
    public void delete(Long id) {
        String url = restProperties.getUrl() + URL + "/delete/"+id;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(url, id);
    }

    @Override
    public List<SeatDTO> find(SeatDTO seatDTO) {
        String url = restProperties.getUrl() + URL + "/find";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> httpEntity = new HttpEntity<>(seatDTO);
        SeatDTO[] response = restTemplate.postForObject(url, httpEntity, SeatDTO[].class);
        return Arrays.asList(response);
    }
}
