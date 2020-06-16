package ClientBusManagement.controller;

import ClientBusManagement.dto.ClientDTO;
import ClientBusManagement.dto.ClientUserIdDTO;
import ClientBusManagement.dto.RouteDTO;
import ClientBusManagement.dto.UserDTO;
import ClientBusManagement.enumeration.UserRole;
import ClientBusManagement.gateway.ClientGateway;
import ClientBusManagement.gateway.UserGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserGateway userGateway;
    private final ClientGateway clientGateway;

    @Autowired
    public UserController(UserGateway userGateway, ClientGateway clientGateway) {
        this.userGateway = userGateway;
        this.clientGateway = clientGateway;
    }

    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    public void getTest(){
        String str = userGateway.test();
        System.out.println(str);
    }

    @GetMapping("/login")
    ModelAndView login(ModelAndView mav){
        mav.addObject("user", new UserDTO());
        mav.setViewName("login");
        return mav;
    }

    @PostMapping("/login")
    public String login(UserDTO userDTO, HttpSession session){
        UserDTO user = userGateway.login(userDTO);
        if(user == null)
            return "redirect:/user/login";

        session.setAttribute("user", user);
        session.setAttribute("userId", user.getId());

        ClientDTO clientSearch = new ClientDTO();
        clientSearch.setUser(user);
        System.out.println(clientSearch.getUser().getId());
        ClientDTO client = clientGateway.find(clientSearch).get(0);
        System.out.println(client.getId());
        session.setAttribute("clientId", client.getId());
        if(user.getRole().compareTo(UserRole.USER) == 0)
            return "redirect:/user/clientPage";
        else {
            if (user.getRole().compareTo(UserRole.ADMINISTRATOR) == 0)
                return "redirect:/user/adminPage";
            else
                return "redirect:/user/login";
        }
   }

    @GetMapping("/register")
    ModelAndView register(ModelAndView mav){
        mav.addObject("user", new UserDTO());
        mav.addObject("client", new ClientDTO());
        mav.setViewName("register");
        return mav;
    }

    @PostMapping("/register")
    public String register(UserDTO userDTO, ClientDTO clientDTO){
        UserDTO user = userGateway.register(userDTO);
        clientDTO.setUser(user);
        clientDTO.setNoOfReservations(0L);
        ClientDTO client = clientGateway.register(clientDTO);
        return "redirect:/user/login";
    }

    @GetMapping("/clientPage")
    ModelAndView clientPage(ModelAndView mav, HttpSession session){
        mav.setViewName("clientPage");
        System.out.println(session.getAttribute("userId"));
        return mav;
    }

    @GetMapping("/clientPageContact")
    ModelAndView clientPageContact(ModelAndView mav){
        mav.setViewName("clientPageContact");
        return mav;
    }

    @GetMapping("/adminPage")
    ModelAndView adminPage(ModelAndView mav){
        mav.setViewName("adminPage");
        return mav;
    }

    @GetMapping("/adminPageClients")
    public ModelAndView adminPageClients(ModelAndView mav){
        List<UserDTO> userDTOS = userGateway.findAll();
        List<ClientDTO> clientDTOS = clientGateway.findAll();
        //List<ClientUserIdDTO> collect = clientDTOS.stream()
              //  .map(this::ToClientUserIdDTO)
              //  .collect(Collectors.toList());
        mav.addObject("users", userDTOS);
        mav.addObject("clients", clientDTOS);
        mav.addObject("user", new UserDTO());
        mav.addObject("client", new ClientDTO());
        mav.setViewName("adminPageClients");
        return mav;
    }

    @GetMapping("/getReport")
    public String getReport(String str){
        userGateway.getReport(str);
        return "redirect:/user/adminPageClients";
    }

    @PostMapping("/save")
    public String save(UserDTO userDTO){
        UserDTO user = userGateway.save(userDTO);
        return "redirect:/user/adminPageClients";
    }

    @GetMapping("/delete")
    public ModelAndView openDelete(ModelAndView mav){
        mav.addObject("user", new UserDTO());
        mav.setViewName("/user/delete");
        return mav;
    }

    @PostMapping("/delete")
    public String delete(UserDTO userDTO){
        userGateway.delete(userDTO.getId());
        return "redirect:/user/adminPageClients";
    }

    private ClientUserIdDTO ToClientUserIdDTO(ClientDTO clientDTO){
        ClientUserIdDTO client = new ClientUserIdDTO();
        client.setId(clientDTO.getId());
        client.setFirstName(clientDTO.getFirstName());
        client.setLastName(clientDTO.getLastName());
        client.setAddress(clientDTO.getAddress());
        client.setCNP(clientDTO.getCNP());
        client.setPhone(clientDTO.getPhone());
        client.setNoOfReservations(clientDTO.getNoOfReservations());
        client.setUserId(clientDTO.getUser().getId());

        return client;
    }

    @GetMapping("/myAccount")
    public ModelAndView saveMyAccount(ModelAndView mav, HttpSession session){
        ClientDTO clientDTO1 = clientGateway.findById((Long) session.getAttribute("clientId"));
        mav.addObject("firstName", clientDTO1.getFirstName());
        mav.addObject("lastName", clientDTO1.getLastName());
        mav.addObject("CNP", clientDTO1.getCNP());
        mav.addObject("address", clientDTO1.getAddress());
        mav.addObject("phone", clientDTO1.getPhone());
        mav.addObject("noOfReservations", clientDTO1.getNoOfReservations());

        UserDTO userDTO1 = userGateway.findById((Long) session.getAttribute("userId"));
        mav.addObject("username", userDTO1.getUsername());
        mav.addObject("password", userDTO1.getPassword());
        mav.setViewName("myAccount");
        return mav;
    }

    @PostMapping("/myAccount")
    public ModelAndView saveMyAccount(ModelAndView mav, UserDTO userDTO, ClientDTO clientDTO, HttpSession session){
        ClientDTO clientDTO1 = clientGateway.findById((Long) session.getAttribute("clientId"));
        mav.addObject("firstName", clientDTO1.getFirstName());
        mav.addObject("lastName", clientDTO1.getLastName());
        mav.addObject("CNP", clientDTO1.getCNP());
        mav.addObject("address", clientDTO1.getAddress());
        mav.addObject("phone", clientDTO.getPhone());
        mav.addObject("noOfReservations", clientDTO1.getNoOfReservations());

        UserDTO userDTO1 = userGateway.findById((Long) session.getAttribute("userId"));
        mav.addObject("username", userDTO1.getUsername());
        mav.addObject("password", userDTO1.getPassword());
        userDTO.setId((Long) session.getAttribute("userId"));

        clientDTO.setUser(userDTO1);
        clientDTO.setId((Long) session.getAttribute("clientId"));
        clientDTO.setNoOfReservations(0L);
        ClientDTO client = clientGateway.save(clientDTO);
        mav.setViewName("myAccount");
        return mav;
    }
}
