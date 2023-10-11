package com.compassuol.sp.challenge.msproducts.controller.exception.getbyidexceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
