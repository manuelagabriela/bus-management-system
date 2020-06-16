package ClientBusManagement.controller;

import ClientBusManagement.dto.*;
import ClientBusManagement.gateway.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/route")
public class RouteController {
    private final RouteGateway routeGateway;
    private final ReservationGateway reservationGateway;
    private final BusGateway busGateway;
    private final SeatGateway seatGateway;
    private final TicketGateway ticketGateway;

    @Autowired
    public RouteController(RouteGateway routeGateway, ReservationGateway reservationGateway, BusGateway busGateway, SeatGateway seatGateway, TicketGateway ticketGateway) {
        this.routeGateway = routeGateway;
        this.reservationGateway = reservationGateway;
        this.busGateway = busGateway;
        this.seatGateway = seatGateway;
        this.ticketGateway = ticketGateway;
    }

    @GetMapping("/adminPageRoute")
    public ModelAndView adminPageRoute(ModelAndView mav){
        List<RouteDTO> routeDTOList = routeGateway.findAll();
        mav.addObject("routes", routeDTOList);
        mav.addObject("route", new RouteDTO());
        mav.setViewName("adminPageRoute");
        return mav;
    }


    @PostMapping("/save")
    public String save(RouteDTO routeDTO){
        RouteDTO route = routeGateway.save(routeDTO);
        return "redirect:/route/adminPageRoute";
    }

    @GetMapping("/delete")
    public ModelAndView openDelete(ModelAndView mav){
        mav.addObject("route", new RouteDTO());
        mav.setViewName("/route/delete");
        return mav;
    }

    @PostMapping("/delete")
    public String delete(RouteDTO routeDTO){
        routeGateway.delete(routeDTO.getId());
        //System.out.println("asadas");
        return "redirect:/route/adminPageRoute";
    }

    @GetMapping("/buyTickets")
    public ModelAndView buyTickets(ModelAndView mav, HttpSession session){
        ReservationDTO reservationDTO = new ReservationDTO();
        //System.out.println(session.getAttribute("clientId"));
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId((Long) session.getAttribute("clientId"));
        reservationDTO.setClient(clientDTO);
        mav.addObject("reservation", reservationDTO);
        mav.addObject("route", new RouteDTO());
        mav.addObject("ticket", new TicketDTO());
        ReservationDTO reservation = reservationGateway.save(reservationDTO);

        System.out.println("resId" + reservation.getId());
        session.setAttribute("reservationId", reservation.getId());

        mav.addObject("buses", new ArrayList<BusDTO>());

        List<TicketDTO> ticketDTOS = ticketGateway.find(reservation);
        session.setAttribute("tickets", ticketDTOS);
        mav.addObject("tickets", ticketDTOS);
        mav.setViewName("buyTickets");
        return mav;
    }

    @PostMapping("/findBuses")
    public ModelAndView findBuses(ModelAndView mav, RouteDTO routeDTO, HttpSession session){
        //System.out.println(routeDTO.getSource() + " " + routeDTO.getDestination());
        List<BusDTO> busDTOS = routeGateway.findBuses(routeDTO);
        for(int it = 0; it < busDTOS.size(); it++){
            SeatDTO seatDTO = new SeatDTO();
            seatDTO.setAvailable(true);
            seatDTO.setBus(busDTOS.get(it));
            busDTOS.get(it).setSeatDTOList(seatGateway.find(seatDTO));
        }
        //System.out.println(busDTOS.get(0).getId());
        session.setAttribute("buses", busDTOS);
        mav.addObject("buses", busDTOS);
        mav.addObject("tickets", session.getAttribute("tickets"));
        mav.setViewName("buyTickets");
        return mav;
    }

    @PostMapping("/findSeats")
    public ModelAndView findSeats(ModelAndView mav, SeatDTO seatDTO, HttpSession session){
        seatDTO.setAvailable(true);
        List<SeatDTO> seatDTOS = seatGateway.find(seatDTO);
        mav.addObject("buses", session.getAttribute("buses"));
        mav.addObject("tickets", session.getAttribute("tickets"));
        mav.setViewName("buyTickets");
        return mav;
    }

    @PostMapping("/addTicket")
    public ModelAndView addTicket(ModelAndView mav, String selectedSeat, HttpSession session){
        ReservationDTO reservation = new ReservationDTO();
        reservation.setId((Long) session.getAttribute("reservationId"));
        TicketDTO ticketDTO = new TicketDTO();
        SeatDTO seatDTO = new SeatDTO();
        seatDTO.setId(Long.parseLong(selectedSeat));
        ticketDTO.setSeat(seatDTO);
        System.out.println("aaa");
        ticketDTO.setReservation(reservation);
        TicketDTO ticket = ticketGateway.save(ticketDTO);

        List<TicketDTO> ticketDTOS = ticketGateway.find(reservation);
        session.setAttribute("tickets", ticketDTOS);
        mav.addObject("buses", session.getAttribute("buses"));
        mav.addObject("tickets", ticketDTOS);

        mav.setViewName("buyTickets");
        return mav;
    }

    @PostMapping("/getTotalPrice")
    public ModelAndView getTotalPrice(ModelAndView mav, HttpSession session){
        mav.addObject("buses", session.getAttribute("buses"));
        mav.addObject("tickets", session.getAttribute("tickets"));

        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId((Long) session.getAttribute("reservationId"));

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId((Long) session.getAttribute("clientId"));
        reservationDTO.setClient(clientDTO);
        Double totalPrice = reservationGateway.getTotalPrice(reservationDTO);
        mav.addObject("totalPrice", totalPrice);
        mav.setViewName("buyTickets");

        return mav;
    }

    @PostMapping("/deleteTicket")
    public ModelAndView deleteTicket(ModelAndView mav, TicketDTO ticketDTO, HttpSession session){
        ticketGateway.delete(ticketDTO.getId());
        ReservationDTO reservation = new ReservationDTO();
        reservation.setId((Long) session.getAttribute("reservationId"));

        List<TicketDTO> ticketDTOS = ticketGateway.find(reservation);
        session.setAttribute("tickets", ticketDTOS);
        mav.addObject("tickets", ticketDTOS);
        mav.addObject("buses", session.getAttribute("buses"));
        mav.setViewName("buyTickets");
        return mav;
    }
}
