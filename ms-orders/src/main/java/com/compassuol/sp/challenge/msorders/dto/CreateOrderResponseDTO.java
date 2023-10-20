package com.compassuol.sp.challenge.msorders.dto;

import com.compassuol.sp.challenge.msorders.constant.PaymentTypeEnum;
import com.compassuol.sp.challenge.msorders.constant.StatusOrderEnum;
import com.compassuol.sp.challenge.msorders.model.AddressModel;
import com.compassuol.sp.challenge.msorders.model.OrderModel;
import com.compassuol.sp.challenge.msorders.model.OrderProductsModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderResponseDTO {
    private Long id;
    private List<OrderProductsModel> products;
    private AddressModel address;
    private PaymentTypeEnum payment_method;
    private Double subtotal_value;
    private Double discount;
    private Double total_value;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("create_date")
    private LocalDateTime createDateTime;
    private StatusOrderEnum status;

    public CreateOrderResponseDTO(OrderModel order) {
        this.id = order.getId();
        this.products = order.getProducts();
        this.address = order.getAddress();
        this.payment_method = order.getPayment_method();
        this.subtotal_value = order.getSubtotal_value();
        this.discount = order.getDiscount();
        this.total_value = order.getTotal_value();
        this.createDateTime = LocalDateTime.now();
        this.status = order.getStatus();
    }
}
