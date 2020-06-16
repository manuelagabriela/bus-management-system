package ClientBusManagement.controller;

import ClientBusManagement.dto.DriverDTO;
import ClientBusManagement.dto.UserDTO;
import ClientBusManagement.gateway.DriverGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@Controller
@RequestMapping("/driver")
public class DriverController {
    private final DriverGateway driverGateway;

    @Autowired
    public DriverController(DriverGateway driverGateway) {
        this.driverGateway = driverGateway;
    }

    @GetMapping("/adminPageEmployees")
    public ModelAndView adminPageEmployees(ModelAndView mav){
        List<DriverDTO> driverDTOList = driverGateway.findAll();
        System.out.println(driverDTOList.size());
        mav.addObject("drivers", driverDTOList);
        mav.addObject("driver", new DriverDTO());
        mav.setViewName("adminPageEmployees");
        return mav;
    }


    @PostMapping("/save")
    public String save(DriverDTO driverDTO){
        DriverDTO driver = driverGateway.save(driverDTO);
        return "redirect:/driver/adminPageEmployees";
    }

    @GetMapping("/delete")
    public ModelAndView openDelete(ModelAndView mav){
        mav.addObject("driver", new DriverDTO());
        mav.setViewName("/driver/delete");
        return mav;
    }

    @PostMapping("/delete")
    public String delete(DriverDTO driverDTO){
        driverGateway.delete(driverDTO.getId());
        //System.out.println("asadas");
        return "redirect:/driver/adminPageEmployees";
    }
}
