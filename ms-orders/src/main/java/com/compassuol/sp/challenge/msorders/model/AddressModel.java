package com.compassuol.sp.challenge.msorders.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@Table(name = "address_tb")
@NoArgsConstructor
@AllArgsConstructor
public class AddressModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    @NotEmpty(message = "street cannot be empty")
    private String street;
    @NotNull(message = "number cannot be null")
    private Integer number;
    private String complement;
    private String city;
    private String state;
    @NotEmpty(message = "postalCode cannot be empty")
    private String postalCode;
}
