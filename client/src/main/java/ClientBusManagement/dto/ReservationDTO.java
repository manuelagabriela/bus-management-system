package ClientBusManagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReservationDTO {

    private Long id;
    private ClientDTO client;

    public ReservationDTO(){

    }
    public ReservationDTO(@JsonProperty("client_id") Long clientId){
        ClientDTO client = new ClientDTO();
        client.setId(clientId);
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }
}
