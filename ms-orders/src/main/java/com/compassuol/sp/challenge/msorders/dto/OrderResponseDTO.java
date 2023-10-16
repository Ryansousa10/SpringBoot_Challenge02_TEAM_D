package com.compassuol.sp.challenge.msorders.dto;

import com.compassuol.sp.challenge.msorders.model.OrderModel;

import java.util.List;

public class OrderResponseDTO {
    private Long id;
    private List<ProductModelDTO> products;
    private AddressDTO address;
    private String paymentMethod;
    private Double subtotalValue;
    private Double discount;
    private Double totalValue;
    private String createdDate;
    private String status;

    public OrderResponseDTO(OrderModel updatedOrder) {
    }

    // Construtor que aceita um objeto OrderModel para preencher os campos.

    // Getters e setters
}

