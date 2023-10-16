package com.compassuol.sp.challenge.msorders.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity
@Table(name = "address_tb")
public class AddressModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String street;
    @NotEmpty
    private Integer number;
    @NotEmpty
    private String complement;
    @NotEmpty
    private String city;
    @NotEmpty
    private String state;
    @NotEmpty
    private String postalCode;



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
    public AddressModel() {

    }
    public AddressModel(Long id, String street, Integer number, String complement, String city, String state, String postalCode) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
    }

}
