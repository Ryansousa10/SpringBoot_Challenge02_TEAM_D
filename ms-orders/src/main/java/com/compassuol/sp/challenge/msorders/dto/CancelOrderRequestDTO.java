package com.compassuol.sp.challenge.msorders.dto;

import java.util.List;

public class CancelOrderRequestDTO {
    private String cancelReason;

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }
}
