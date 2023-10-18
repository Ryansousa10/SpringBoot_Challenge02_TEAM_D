package com.compassuol.sp.challenge.msorders.controller.exception.errorTypes;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message) {
        super(message);
    }
}
