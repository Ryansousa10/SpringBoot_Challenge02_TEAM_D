package com.compassuol.sp.challenge.msproducts.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
