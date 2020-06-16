package ClientBusManagement.gateway;

import ClientBusManagement.dto.DriverDTO;

import java.util.List;

public interface DriverGateway {
    List<DriverDTO> findAll();

    DriverDTO save(DriverDTO driverDTO);

    void delete(Long id);
}
