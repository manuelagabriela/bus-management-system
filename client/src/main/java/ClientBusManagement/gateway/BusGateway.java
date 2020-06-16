package ClientBusManagement.gateway;

import ClientBusManagement.dto.BusDTO;
import ClientBusManagement.dto.RouteDTO;

import java.util.List;

public interface BusGateway {
    List<BusDTO> findAll();

    BusDTO save(BusDTO busDTO);

    void delete(Long id);

    List<BusDTO> find(BusDTO busDTO);
}
