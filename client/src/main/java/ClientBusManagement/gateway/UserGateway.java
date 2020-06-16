package ClientBusManagement.gateway;

import ClientBusManagement.dto.RouteDTO;
import ClientBusManagement.dto.UserDTO;

import java.util.List;

public interface UserGateway {
    String test();

    UserDTO login(UserDTO userDTO);

    UserDTO register(UserDTO userDTO);

    List<UserDTO> findAll();

    UserDTO save(UserDTO userDTO);

    void delete(Long id);

    UserDTO findById(Long id);

    void getReport(String reportType);
}
