package ClientBusManagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Date;
import java.time.LocalDateTime;

public class BusDateDTO {

    private Long id;
    private String firm;
    private String date;
    private DriverDTO driver;
    private RouteDTO route;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public DriverDTO getDriver() {
        return driver;
    }

    public void setDriver(DriverDTO driver) {
        this.driver = driver;
    }

    public RouteDTO getRoute() {
        return route;
    }

    public void setRoute(RouteDTO route) {
        this.route = route;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
