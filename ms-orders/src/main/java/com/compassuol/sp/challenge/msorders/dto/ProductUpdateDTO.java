package com.compassuol.sp.challenge.msorders.dto;

public class ProductUpdateDTO {
    private Double price;
    private Integer quantity;

    // Construtor vazio
    public ProductUpdateDTO() {
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    // Outros getters e setters, se necessário
}
