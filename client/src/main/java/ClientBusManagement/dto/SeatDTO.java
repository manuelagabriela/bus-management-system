package ClientBusManagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class SeatDTO {

    private Long id;
    private Boolean available;
    private Long number;
    private BusDTO bus;

    public SeatDTO(){}

    public SeatDTO(@JsonProperty("available") Boolean available,
                   @JsonProperty("number") Long number,
                   @JsonProperty("bus_id") Long busId){

        this.available = available;
        this.number = number;
        BusDTO bus = new BusDTO();
        bus.setId(busId);
        this.bus = bus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public BusDTO getBus() {
        return bus;
    }

    public void setBus(BusDTO bus) {
        this.bus = bus;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }
}
