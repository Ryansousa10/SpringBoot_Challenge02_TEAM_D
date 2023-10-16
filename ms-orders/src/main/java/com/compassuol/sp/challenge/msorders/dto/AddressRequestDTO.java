package com.compassuol.sp.challenge.msorders.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddressRequestDTO {

    @NotEmpty(message = "street address cannot be null or empty")
    private String street;
    @NotNull(message = "number address cannot be null or empty")
    private Integer number;
    @NotEmpty(message = "postalCode address cannot be null or empty")
    private String postalCode;
}
