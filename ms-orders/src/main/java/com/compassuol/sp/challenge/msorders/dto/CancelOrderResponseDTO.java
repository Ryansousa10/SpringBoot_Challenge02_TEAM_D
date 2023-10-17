package com.compassuol.sp.challenge.msorders.dto;

import lombok.Data;

import java.util.List;

@Data
public class CancelOrderResponseDTO {
    private List<ProductInfo> products;
    private AddressInfo address;
    private String paymentMethod;
    private Double subtotalValue;
    private Double discount;
    private Double totalValue;
    private String createdDate;
    private String status;
    private String cancelReason;
    private String cancelDate;

    @Data
    public static class ProductInfo {
        private Long product_id;
        private int quantity;
    }

    @Data
    public static class AddressInfo {
        private String street;
        private Integer number;
        private String complement;
        private String city;
        private String state;
        private String postalCode;
    }
}


