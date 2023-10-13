package com.compassuol.sp.challenge.msproducts.controller.exception.errorTypes;

public class ProductNameExistsException extends RuntimeException {
    public ProductNameExistsException(String message) {
        super(message);
    }
}
