package com.compassuol.sp.challenge.msproducts.controller.exception.getExceptions;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
@Getter
public class ProductFetchException extends RuntimeException {

    private final int code;
    private final String status;
    private final String message;
    private final List<String> details;

    public ProductFetchException(String message) {
        super(message);
        this.code = 500; // Código de status HTTP 500 (Internal Server Error)
        this.status = "Internal Server Error";
        this.message = message;
        this.details = new ArrayList<>();
    }
}
