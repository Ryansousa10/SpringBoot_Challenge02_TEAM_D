package com.compassuol.sp.challenge.msorders.dto;

import com.compassuol.sp.challenge.msorders.constant.PaymentTypeEnum;
import com.compassuol.sp.challenge.msorders.model.OrderProductsModel;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class RequestOrderDTO {

    @NotNull(message = "products field cannot be null")
    @Size(min = 1, message = "you have to put one or more products")
    @Valid
    private List<OrderProductsModel> products;
    @NotNull(message = "address field cannot be null")
    @Valid
    private AddressRequestDTO address;
    @NotNull(message = "payment method field cannot be null")
    @Enumerated(EnumType.STRING)
    private PaymentTypeEnum payment_method;
    public RequestOrderDTO() {

    }
}
