package com.compassuol.sp.challenge.msorders.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "order_products_tb")
@AllArgsConstructor
public class OrderProductsModel {
    @Id
    private int product_id;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "principal_class_id") //foreign key
    private OrderModel orderModelRelation;

    public OrderProductsModel() {
        // Mantenha o construtor vazio do seu ramo atual.
    }

    public void setName(String name) {
    }
}
