package com.compassuol.sp.challenge.msorders.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CancelOrderRequestDTO {
    private String cancelReason;
}