package ClientBusManagement.gateway.impl;

import ClientBusManagement.conf.RestProperties;
import ClientBusManagement.dto.ReservationDTO;
import ClientBusManagement.dto.SeatDTO;
import ClientBusManagement.gateway.ReservationGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ReservationGatewayImpl implements ReservationGateway {
    private final RestProperties restProperties;
    private final String URL = "/reservation";


    @Autowired
    public ReservationGatewayImpl(RestProperties restProperties) {
        this.restProperties = restProperties;
    }

    @Override
    public ReservationDTO save(ReservationDTO reservationDTO) {
        String url = restProperties.getUrl() + URL + "/save";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> httpEntity = new HttpEntity<>(reservationDTO);
        ReservationDTO response = restTemplate.postForObject(url, httpEntity, ReservationDTO.class);
        return response;
    }

    @Override
    public Double getTotalPrice(ReservationDTO reservationDTO) {
        String url = restProperties.getUrl() + URL + "/totalPrice";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> httpEntity = new HttpEntity<>(reservationDTO);
        Double response = restTemplate.postForObject(url, httpEntity, Double.class);
        return response;
    }
}
