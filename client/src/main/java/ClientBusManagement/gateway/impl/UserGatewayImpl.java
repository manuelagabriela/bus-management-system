package ClientBusManagement.gateway.impl;

import ClientBusManagement.conf.RestProperties;
import ClientBusManagement.dto.ClientDTO;
import ClientBusManagement.dto.DriverDTO;
import ClientBusManagement.dto.UserDTO;
import ClientBusManagement.enumeration.UserRole;
import ClientBusManagement.gateway.ClientGateway;
import ClientBusManagement.gateway.UserGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class UserGatewayImpl implements UserGateway {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginGatewayImpl.class);
    private final String URL = "/user";

    private final RestProperties restProperties;
    private final ClientGateway clientGateway;

    @Autowired
    public UserGatewayImpl(RestProperties restProperties, ClientGateway clientGateway) {
        this.restProperties = restProperties;
        this.clientGateway = clientGateway;
    }

    @Override
    public String test() {
        LOGGER.info("Testing test() method, connection to backend api");
        String url = restProperties.getUrl() + URL + "/test";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        String forObject = restTemplate.getForObject(url, String.class);
        return forObject;
    }

    @Override
    public UserDTO login(UserDTO userDTO) {
        String url = restProperties.getUrl() + URL + "/login";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> httpEntity = new HttpEntity<>(userDTO);
        UserDTO response = restTemplate.postForObject(url, httpEntity, UserDTO.class);
        return response;
    }

    @Override
    public UserDTO register(UserDTO userDTO){
        userDTO.setRole(UserRole.USER);
        System.out.println(userDTO.getUsername() + " " + userDTO.getPassword());
        String url = restProperties.getUrl() + URL + "/save";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> httpEntity = new HttpEntity<>(userDTO);
        UserDTO response = restTemplate.postForObject(url, httpEntity, UserDTO.class);
        return response;
    }

    @Override
    public List<UserDTO> findAll() {
        String url = restProperties.getUrl() + URL + "/list";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserDTO[]> forEntity = restTemplate.getForEntity(url, UserDTO[].class);
        UserDTO[] response = restTemplate.getForObject(url, UserDTO[].class);
        return Arrays.asList(response);
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        String url = restProperties.getUrl() + URL + "/save";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> httpEntity = new HttpEntity<>(userDTO);
        UserDTO response = restTemplate.postForObject(url, httpEntity, UserDTO.class);
        return response;
    }

    @Override
    public void delete(Long id) {
        String url = restProperties.getUrl() + URL + "/delete/"+id;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(url, id);
    }

    @Override
    public UserDTO findById(Long id) {
        String url = restProperties.getUrl() + URL + "/findById/"+id;
        RestTemplate restTemplate = new RestTemplate();
        UserDTO response = restTemplate.getForObject(url, UserDTO.class);
        return response;
    }

    @Override
    public void getReport(String reportType) {
        String url = restProperties.getUrl() + URL + "/raport?str=" + reportType;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(url, void.class);
    }
}
