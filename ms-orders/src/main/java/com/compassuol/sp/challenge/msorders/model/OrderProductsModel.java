package com.compassuol.sp.challenge.msorders.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Embeddable
public class OrderProductsModel {
    private int product_id;
    private int quantity;

    public OrderProductsModel() {}
}
