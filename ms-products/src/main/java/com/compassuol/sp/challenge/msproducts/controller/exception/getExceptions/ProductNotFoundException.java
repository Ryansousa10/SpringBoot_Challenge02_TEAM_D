package com.compassuol.sp.challenge.msproducts.controller.exception.getExceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
