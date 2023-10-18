package com.compassuol.sp.challenge.msfeedback.controller.exception.errorTypes;

public class OrderCancellationNotAllowedException extends RuntimeException {
    public OrderCancellationNotAllowedException(String message) {
        super(message);
    }
}
