package com.compassuol.sp.challenge.msorders.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class CancelOrderRequestDTO {
    private String cancelReason;

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }
}
