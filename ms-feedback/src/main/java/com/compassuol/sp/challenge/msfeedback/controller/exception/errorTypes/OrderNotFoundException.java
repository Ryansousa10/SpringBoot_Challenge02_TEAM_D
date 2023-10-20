package com.compassuol.sp.challenge.msfeedback.controller.exception.errorTypes;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message) {
        super(message);
    }
}
