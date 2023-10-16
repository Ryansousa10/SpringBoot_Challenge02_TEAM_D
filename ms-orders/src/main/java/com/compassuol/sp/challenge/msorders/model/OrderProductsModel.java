package com.compassuol.sp.challenge.msorders.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "order_products_tb")
public class OrderProductsModel {
    @Id
    private Long product_id;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "principal_class_id") //foreign key
    private OrderModel orderModelRelation;

    public OrderProductsModel() {
    }

    public void setName(String name) {
    }
}
