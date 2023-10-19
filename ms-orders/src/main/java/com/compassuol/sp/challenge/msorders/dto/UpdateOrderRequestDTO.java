package com.compassuol.sp.challenge.msorders.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateOrderRequestDTO {
    private String status; // O status do pedido (CONFIRMED, SENT, CANCELED)
    private String deliveryDate; // A nova data de entrega no formato ISO 8601
}
