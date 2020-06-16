package com.VulpeManuela.BusManagement.entity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "route")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String source;
    private String destination;
    private Double price;
    @OneToMany(mappedBy = "route", cascade = CascadeType.REMOVE)
    List<Bus> buses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
