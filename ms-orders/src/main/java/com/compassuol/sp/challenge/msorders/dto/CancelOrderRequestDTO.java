package com.compassuol.sp.challenge.msorders.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CancelOrderRequestDTO {
    private String cancelReason;
}
