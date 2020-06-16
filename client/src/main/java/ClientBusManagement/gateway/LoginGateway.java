package ClientBusManagement.gateway;

import ClientBusManagement.dto.UserDTO;

public interface LoginGateway {
    Long login(UserDTO userDTO);
}
