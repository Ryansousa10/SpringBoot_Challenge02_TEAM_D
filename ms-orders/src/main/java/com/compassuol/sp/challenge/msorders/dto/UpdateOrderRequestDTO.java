package com.compassuol.sp.challenge.msorders.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateOrderRequestDTO {
    @NotEmpty(message = "status field cannot be empty")
    private String status;
}