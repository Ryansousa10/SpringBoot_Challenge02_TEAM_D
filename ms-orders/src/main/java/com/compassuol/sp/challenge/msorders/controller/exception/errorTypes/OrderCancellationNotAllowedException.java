package com.compassuol.sp.challenge.msorders.controller.exception.errorTypes;

public class OrderCancellationNotAllowedException extends RuntimeException {
    public OrderCancellationNotAllowedException(String message) {
        super(message);
    }
}
