package ClientBusManagement.gateway;

import ClientBusManagement.dto.ClientDTO;
import ClientBusManagement.dto.UserDTO;

import java.util.List;

public interface ClientGateway {
    ClientDTO register(ClientDTO clientDTO);

    List<ClientDTO> findAll();

    ClientDTO save(ClientDTO clientDTO);

    List<ClientDTO> find(ClientDTO clientDTO);

    void delete(Long id);

    ClientDTO findById(Long id);
}
