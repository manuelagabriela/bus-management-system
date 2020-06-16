package ClientBusManagement.controller;

import ClientBusManagement.dto.*;
import ClientBusManagement.gateway.ReservationGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Component
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationGateway reservationGateway;

    @Autowired
    public ReservationController(ReservationGateway reservationGateway) {
        this.reservationGateway = reservationGateway;
    }

    @GetMapping("/buyTickets")
    public ModelAndView buyTickets(ModelAndView mav, HttpSession session){
        ReservationDTO reservationDTO = new ReservationDTO();
        //System.out.println(session.getAttribute("clientId"));
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId((Long) session.getAttribute("clientId"));
        reservationDTO.setClient(clientDTO);
        mav.addObject("reservation", reservationDTO);
        ReservationDTO reservation = reservationGateway.save(reservationDTO);
        mav.addObject("buses", new ArrayList<BusDTO>());
        mav.setViewName("buyTickets");
        return mav;
    }
}
