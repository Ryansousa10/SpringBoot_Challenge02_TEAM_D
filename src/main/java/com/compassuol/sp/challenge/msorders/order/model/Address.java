package com.compassuol.sp.challenge.msorders.order.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "address_tb")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String number;
    private String complement;
    private String postalCode;
    private String city;
    private String state;
}