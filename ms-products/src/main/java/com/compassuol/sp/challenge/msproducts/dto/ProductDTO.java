package com.compassuol.sp.challenge.msproducts.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;


@Getter
@Setter
@Validated
@AllArgsConstructor
public class ProductDTO {

    @NotEmpty(message = "field 'name' is mandatory")
    String name;
    @NotNull(message = "field 'description' cannot be null")
    @Size(min = 10, message = "field 'description' must have at least 10 characters")
    String description;
    @NotNull(message = "field 'value' cannot be null")
    @Min(value = 0L, message = "field 'value' cannot negative")
    Double value;
}
