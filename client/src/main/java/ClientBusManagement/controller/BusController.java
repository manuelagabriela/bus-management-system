package ClientBusManagement.controller;

import ClientBusManagement.dto.*;
import ClientBusManagement.gateway.BusGateway;
import ClientBusManagement.gateway.SeatGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/bus")
public class BusController {
    private final BusGateway busGateway;
    private final SeatGateway seatGateway;

    @Autowired
    public BusController(BusGateway busGateway, SeatGateway seatGateway) {
        this.busGateway = busGateway;
        this.seatGateway = seatGateway;
    }

    @GetMapping("/adminPageBus")
    public ModelAndView adminPageBus(ModelAndView mav){
        List<BusDTO> busDTOS = busGateway.findAll();
        List<SeatDTO> seatDTOS = seatGateway.findAll();

        mav.addObject("seats", seatDTOS);
        mav.addObject("buses", busDTOS);
        mav.addObject("seat", new SeatDTO());
        mav.addObject("bus", new BusDTO());
        mav.setViewName("adminPageBus");
        return mav;
    }


    @PostMapping("/save")
    public String save(BusDateDTO busDateDTO){
        BusDTO busDTO = new BusDTO();
        busDTO.setId(busDateDTO.getId());
        busDTO.setFirm(busDateDTO.getFirm());

        BusDTO busDTO1 = new BusDTO();
        busDTO1.setDriver(busDateDTO.getDriver());
        busDTO1.setRoute(busDateDTO.getRoute());
        List<BusDTO> busDTOS = busGateway.find(busDTO1);

        busDTO.setDriver(busDTOS.get(0).getDriver());
        busDTO.setRoute(busDTOS.get(0).getRoute());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime localDate = LocalDateTime.parse(busDateDTO.getDate(), formatter);
        busDTO.setDate(localDate);

        BusDTO bus = busGateway.save(busDTO);
        return "redirect:/bus/adminPageBus";
    }

    @GetMapping("/delete")
    public ModelAndView openDelete(ModelAndView mav){
        mav.addObject("bus", new BusDTO());
        mav.setViewName("/bus/delete");
        return mav;
    }

    @PostMapping("/delete")
    public String delete(BusDTO busDTO){
        busGateway.delete(busDTO.getId());
        return "redirect:/bus/adminPageBus";
    }
}
