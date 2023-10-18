package com.compassuol.sp.challenge.msorders.exception.errorTypes;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message) {
        super(message);
    }
}
