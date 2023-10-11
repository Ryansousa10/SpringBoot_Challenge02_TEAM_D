package com.compassuol.sp.challenge.msproducts.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "products_tb")
@NoArgsConstructor
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Size(min = 10)
    private String description;
    @Column(name = "price")
    @Min(value = 0L)
    private Double value;

    public ProductModel(String name, String description, Double value) {
        this.name = name;
        this.description = description;
        this.value = value;
    }
}