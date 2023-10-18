package com.compassuol.sp.challenge.msorders.exception.errorTypes;

public class OrderCancellationNotAllowedException extends RuntimeException {
    public OrderCancellationNotAllowedException(String message) {
        super(message);
    }
}
