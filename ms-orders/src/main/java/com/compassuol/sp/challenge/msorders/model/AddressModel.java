package com.compassuol.sp.challenge.msorders.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity
@Table(name = "address_tb")
@AllArgsConstructor
public class AddressModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;
    @NotEmpty(message = "street cannot be empty")
    private String street;
    @NotNull(message = "number cannot be null")
    private Integer number;
    private String complement;
    private String city;
    private String state;
    @NotEmpty(message = "postalCode cannot be empty")
    private String postalCode;

    public AddressModel() {}

    public AddressModel(String street, Integer number, String complement,
                        String city, String state, String postalCode) {
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "AddressModel{" +
                "street='" + street + '\'' +
                ", number=" + number +
                ", complement='" + complement + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}
