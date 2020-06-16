package ClientBusManagement.gateway;

import ClientBusManagement.dto.ReservationDTO;

public interface ReservationGateway {
    ReservationDTO save(ReservationDTO reservationDTO);

    Double getTotalPrice(ReservationDTO reservationDTO);
}
