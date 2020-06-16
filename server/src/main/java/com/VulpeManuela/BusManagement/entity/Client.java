package com.VulpeManuela.BusManagement.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String CNP;
    private String address;
    private String phone;
    private Long noOfReservations;
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getNoOfReservations() {
        return noOfReservations;
    }

    public void setNoOfReservations(Long noOfReservations) {
        this.noOfReservations = noOfReservations;
    }
}
