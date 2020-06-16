package ClientBusManagement.controller;

import ClientBusManagement.dto.ClientDTO;
import ClientBusManagement.dto.UserDTO;
import ClientBusManagement.gateway.ClientGateway;
import ch.qos.logback.core.net.server.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientsController {
    private final ClientGateway clientGateway;

    @Autowired
    public ClientsController(ClientGateway clientGateway) {
        this.clientGateway = clientGateway;
    }

    @PostMapping("/save")
    public String save(ClientDTO clientDTO){
        ClientDTO clientDTO1 = new ClientDTO();
        clientDTO1.setUser(clientDTO.getUser());
        List<ClientDTO> clientDTOList = clientGateway.find(clientDTO1);
        System.out.println(clientDTOList.size());
        clientDTO.setUser(clientDTOList.get(0).getUser());
        ClientDTO client = clientGateway.save(clientDTO);
        return "redirect:/user/adminPageClients";
    }

    @GetMapping("/delete")
    public ModelAndView openDelete(ModelAndView mav){
        mav.addObject("client", new ClientDTO());
        mav.setViewName("/client/delete");
        return mav;
    }

    @PostMapping("/delete")
    public String delete(ClientDTO clientDTO){
        clientGateway.delete(clientDTO.getId());
        return "redirect:/user/adminPageClients";
    }
}
