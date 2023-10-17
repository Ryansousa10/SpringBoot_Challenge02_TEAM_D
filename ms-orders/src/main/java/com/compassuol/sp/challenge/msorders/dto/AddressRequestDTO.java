package com.compassuol.sp.challenge.msorders.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Size(max = 9, message = "postalCode exceed max characters")
    private String postalCode;
}
