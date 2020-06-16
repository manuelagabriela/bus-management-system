package com.VulpeManuela.BusManagement.dto;

import com.VulpeManuela.BusManagement.entity.Reservation;
import com.VulpeManuela.BusManagement.entity.Seat;
import com.VulpeManuela.BusManagement.entity.Ticket;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.sql.Date;

public class TicketDTO {

    private Long id;
    private ReservationDTO reservation;
    private SeatDTO seat;

    public Long getId() {
        return id;
    }

    public TicketDTO(){
    }

    public TicketDTO(@JsonProperty("reservation_id") Long reservationId,
                     @JsonProperty("seat_id") Long seatId){

        ReservationDTO reservation = new ReservationDTO();
        reservation.setId(reservationId);
        this.reservation = reservation;

        SeatDTO seat = new SeatDTO();
        seat.setId(seatId);
        this.seat = seat;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReservationDTO getReservation() {
        return reservation;
    }

    public void setReservation(ReservationDTO reservation) {
        this.reservation = reservation;
    }

    public SeatDTO getSeat() {
        return seat;
    }

    public void setSeat(SeatDTO seat) {
        this.seat = seat;
    }
}
