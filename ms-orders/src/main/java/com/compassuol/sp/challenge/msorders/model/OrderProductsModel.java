package com.compassuol.sp.challenge.msorders.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class OrderProductsModel {
    @NotNull(message = "product_id field cannot be null")
    private Long product_id;
    @NotNull(message = "quantity field cannot be null")
    private Long quantity;
}
