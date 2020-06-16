package ClientBusManagement.gateway.impl;

import ClientBusManagement.conf.RestProperties;
import ClientBusManagement.dto.ReservationDTO;
import ClientBusManagement.dto.SeatDTO;
import ClientBusManagement.dto.TicketDTO;
import ClientBusManagement.gateway.TicketGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class TicketGatewayImpl implements TicketGateway {
    private final RestProperties restProperties;
    private final String URL = "/ticket";

    @Autowired
    public TicketGatewayImpl(RestProperties restProperties) {
        this.restProperties = restProperties;
    }

    @Override
    public List<TicketDTO> findAll() {
        String url = restProperties.getUrl() + URL + "/list";
        RestTemplate restTemplate = new RestTemplate();
        TicketDTO[] response = restTemplate.getForObject(url, TicketDTO[].class);
        // System.out.println(response.length);
        return Arrays.asList(response);
    }

    @Override
    public TicketDTO save(TicketDTO ticketDTO) {
        String url = restProperties.getUrl() + URL + "/save";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> httpEntity = new HttpEntity<>(ticketDTO);
        TicketDTO response = restTemplate.postForObject(url, httpEntity, TicketDTO.class);
        return response;
    }

    @Override
    public void delete(Long id) {
        String url = restProperties.getUrl() + URL + "/delete/"+id;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(url, id);
    }

    @Override
    public List<TicketDTO> find(ReservationDTO reservationDTO) {
        String url = restProperties.getUrl() + URL + "/find";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> httpEntity = new HttpEntity<>(reservationDTO);
        TicketDTO[] response = restTemplate.postForObject(url, httpEntity, TicketDTO[].class);
        // System.out.println(response.length);
        return Arrays.asList(response);
    }
}
