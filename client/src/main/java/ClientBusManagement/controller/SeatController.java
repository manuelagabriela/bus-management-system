package ClientBusManagement.controller;

import ClientBusManagement.dto.ClientDTO;
import ClientBusManagement.dto.SeatDTO;
import ClientBusManagement.gateway.SeatGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Component
@RequestMapping("/seat")
public class SeatController {
    private final SeatGateway seatGateway;

    @Autowired
    public SeatController(SeatGateway seatGateway) {
        this.seatGateway = seatGateway;
    }

    @PostMapping("/save")
    public String save(SeatDTO seatDTO){
        SeatDTO seat = seatGateway.save(seatDTO);
        return "redirect:/bus/adminPageBus";
    }

    @GetMapping("/delete")
    public ModelAndView openDelete(ModelAndView mav){
        mav.addObject("seat", new SeatDTO());
        mav.setViewName("/seat/delete");
        return mav;
    }

    @PostMapping("/delete")
    public String delete(SeatDTO seatDTO){
        seatGateway.delete(seatDTO.getId());
        return "redirect:/bus/adminPageBus";
    }
}
