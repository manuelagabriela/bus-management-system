package ClientBusManagement.gateway;

import ClientBusManagement.dto.ReservationDTO;
import ClientBusManagement.dto.SeatDTO;
import ClientBusManagement.dto.TicketDTO;

import java.util.List;

public interface TicketGateway {
    List<TicketDTO> findAll();

   TicketDTO save(TicketDTO ticketDTO);

    void delete(Long id);

    List<TicketDTO> find(ReservationDTO reservationDTO);
}
