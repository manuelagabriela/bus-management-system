package ClientBusManagement.gateway.impl;

import ClientBusManagement.conf.RestProperties;
import ClientBusManagement.dto.ClientDTO;
import ClientBusManagement.dto.UserDTO;
import ClientBusManagement.gateway.ClientGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class ClientGatewayImpl implements ClientGateway {
    private final String URL = "/client";

    private final RestProperties restProperties;

    @Autowired
    public ClientGatewayImpl(RestProperties restProperties) {
        this.restProperties = restProperties;
    }

    @Override
    public ClientDTO register(ClientDTO clientDTO) {
        String url = restProperties.getUrl() + URL + "/save";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> httpEntity = new HttpEntity<>(clientDTO);
        ClientDTO response = restTemplate.postForObject(url, httpEntity, ClientDTO.class);
        return response;
    }

    @Override
    public List<ClientDTO> findAll() {
        String url = restProperties.getUrl() + URL + "/list";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ClientDTO[]> forEntity = restTemplate.getForEntity(url, ClientDTO[].class);
        ClientDTO[] response = restTemplate.getForObject(url, ClientDTO[].class);
        return Arrays.asList(response);
    }

    @Override
    public ClientDTO save(ClientDTO clientDTO) {
        String url = restProperties.getUrl() + URL + "/save";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> httpEntity = new HttpEntity<>(clientDTO);
        ClientDTO response = restTemplate.postForObject(url, httpEntity, ClientDTO.class);
        return response;
    }

    @Override
    public List<ClientDTO> find(ClientDTO clientDTO) {
        String url = restProperties.getUrl() + URL + "/find";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> httpEntity = new HttpEntity<>(clientDTO);
        ClientDTO[] response = restTemplate.postForObject(url, httpEntity, ClientDTO[].class);
        return Arrays.asList(response);
    }

    @Override
    public void delete(Long id) {
        String url = restProperties.getUrl() + URL + "/delete/"+id;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(url, id);
    }

    @Override
    public ClientDTO findById(Long id) {
        String url = restProperties.getUrl() + URL + "/findById/"+id;
        RestTemplate restTemplate = new RestTemplate();
        ClientDTO response = restTemplate.getForObject(url, ClientDTO.class);
        return response;
    }
}
