package ClientBusManagement.gateway.impl;

import ClientBusManagement.conf.RestProperties;
import ClientBusManagement.dto.UserDTO;
import ClientBusManagement.gateway.LoginGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class LoginGatewayImpl implements LoginGateway {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginGatewayImpl.class);
    private final String URL = "/login";

    private final RestProperties restProperties;

    @Autowired
    public LoginGatewayImpl(RestProperties restProperties) {
        this.restProperties = restProperties;
    }
    @Override
    public Long login(UserDTO userDTO) {
        String url = restProperties.getUrl() + URL;
        RestTemplate restTemplate = new RestTemplate();
        Long response = restTemplate.getForObject(url, Long.class);
        return response;
    }
}
