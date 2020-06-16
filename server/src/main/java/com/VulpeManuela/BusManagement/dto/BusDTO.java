package com.VulpeManuela.BusManagement.dto;

import com.VulpeManuela.BusManagement.entity.Driver;
import com.VulpeManuela.BusManagement.entity.Route;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

public class BusDTO {

    private Long id;
    private String firm;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;
    private DriverDTO driver;
    private RouteDTO route;

    public BusDTO(){

    }

    public BusDTO(@JsonProperty("firm") String firm,
                   @JsonProperty("date") LocalDateTime date,
                   @JsonProperty("driver_id") Long driverId,
                   @JsonProperty("route_id") Long routeId){
        this.firm = firm;
        this.date = date;
        DriverDTO driver = new DriverDTO();
        driver.setId(driverId);
        this.driver = driver;

        RouteDTO route = new RouteDTO();
        route.setId(routeId);
        this.route = route;
    }

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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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
}
