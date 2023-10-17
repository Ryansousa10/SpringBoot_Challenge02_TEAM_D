package com.compassuol.sp.challenge.msorders.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductModelDTO {

    private Long id;
    private String name;
    private String description;
    private Double value;
}
