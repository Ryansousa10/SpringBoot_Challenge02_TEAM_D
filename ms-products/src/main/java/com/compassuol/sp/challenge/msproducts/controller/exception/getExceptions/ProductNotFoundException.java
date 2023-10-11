package com.compassuol.sp.challenge.msproducts.controller.exception.getExceptions;

import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
public class ProductNotFoundException extends RuntimeException {
    private final int code;
    private final String status;
    private final String message;
    private final List<String> details;

    public ProductNotFoundException(String message) {
        super(message);
        this.code = 404; // Código de status HTTP 404 (Not Found)
        this.status = "NOT FOUND";
        this.message = message;
        this.details = Collections.singletonList("Resource not found");
    }
}