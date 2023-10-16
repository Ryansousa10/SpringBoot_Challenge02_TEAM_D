package com.compassuol.sp.challenge.msorders.dto;

import com.compassuol.sp.challenge.msorders.constant.StatusOrderEnum;

import java.util.List;

public class RequestOrderUpdateDTO {
    private List<ProductModelDTO> products;
    private AddressDTO address;
    private String paymentMethod;

    // Getters e setters
    public List<ProductModelDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductModelDTO> products) {
        this.products = products;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    private StatusOrderEnum status;

    public StatusOrderEnum getStatus() {
        return status;
    }

    public void setStatus(StatusOrderEnum status) {
        this.status = status;
    }
}
