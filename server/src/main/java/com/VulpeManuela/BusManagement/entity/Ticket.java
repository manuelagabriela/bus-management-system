package com.VulpeManuela.BusManagement.entity;

import javax.persistence.*;

@Entity(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name="reservation_id", nullable=false)
    private Reservation reservation;
    @OneToOne
    @JoinColumn(name = "seat_id", referencedColumnName = "id")
    private Seat seat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
}
