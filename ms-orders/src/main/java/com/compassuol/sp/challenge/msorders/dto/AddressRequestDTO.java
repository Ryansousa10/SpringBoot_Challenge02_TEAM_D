package com.compassuol.sp.challenge.msorders.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddressRequestDTO {

    @NotEmpty
    private String street;
    @NotEmpty
    private Integer number;
    @NotEmpty
    private String postalCode;
}
