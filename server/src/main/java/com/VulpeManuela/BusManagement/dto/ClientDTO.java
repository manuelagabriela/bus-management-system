package com.VulpeManuela.BusManagement.dto;

import com.VulpeManuela.BusManagement.entity.Client;
import com.VulpeManuela.BusManagement.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

public class ClientDTO {

    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String CNP;
    private String address;
    private String phone;
    private Long noOfReservations;
    private UserDTO user;

    public ClientDTO(){
    }

    public ClientDTO(@JsonProperty ("firstName") String firstName,
                     @JsonProperty ("lastName") String lastName,
                     @JsonProperty ("CNP") String CNP,
                     @JsonProperty ("address") String address,
                     @JsonProperty ("phone") String phone,
                     @JsonProperty ("noOfReservations") Long noOfReservations,
                     @JsonProperty("user_id") Long userId){
        this.firstName = firstName;
        this.lastName = lastName;
        this.CNP = CNP;
        this.address = address;
        this.phone = phone;
        this.noOfReservations = noOfReservations;

        UserDTO user = new UserDTO();
        user.setId(userId);
        this.user = user;
    }

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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Long getNoOfReservations() {
        return noOfReservations;
    }

    public void setNoOfReservations(Long noOfReservations) {
        this.noOfReservations = noOfReservations;
    }
}
