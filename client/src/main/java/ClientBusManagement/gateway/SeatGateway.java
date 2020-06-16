package ClientBusManagement.gateway;

import ClientBusManagement.dto.BusDTO;
import ClientBusManagement.dto.SeatDTO;

import java.util.List;

public interface SeatGateway {
    List<SeatDTO> findAll();

    SeatDTO save(SeatDTO seatDTO);

    void delete(Long id);

    List<SeatDTO> find(SeatDTO seatDTO);
}
