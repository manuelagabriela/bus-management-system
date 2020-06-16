package ClientBusManagement.gateway;

import ClientBusManagement.dto.BusDTO;
import ClientBusManagement.dto.RouteDTO;

import java.util.List;

public interface RouteGateway {
    List<RouteDTO> findAll();

    RouteDTO save(RouteDTO routeDTO);

    void delete(Long id);

    List<BusDTO> findBuses(RouteDTO routeDTO);
}
