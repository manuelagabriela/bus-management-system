package com.VulpeManuela.BusManagement.entity;

import javax.persistence.*;

@Entity(name = "seat")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Boolean available;
    private Long number;
    @ManyToOne
    @JoinColumn(name = "bus_id", nullable=false)
    private Bus bus;

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

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }
}
