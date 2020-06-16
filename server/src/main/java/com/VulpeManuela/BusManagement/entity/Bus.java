package com.VulpeManuela.BusManagement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity(name = "bus")
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firm;
    @Column(name = "date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "driver_id", nullable=false)
    private Driver driver;
    @ManyToOne
    @JoinColumn(name = "route_id", nullable=false)
    private Route route;
    @OneToMany(mappedBy = "bus", cascade = CascadeType.REMOVE)
    List<Seat> seats;

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

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
}
