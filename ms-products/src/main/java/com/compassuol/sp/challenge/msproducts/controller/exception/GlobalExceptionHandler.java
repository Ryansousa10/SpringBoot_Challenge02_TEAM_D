package com.compassuol.sp.challenge.msproducts.controller.exception;

import com.compassuol.sp.challenge.msproducts.controller.exception.getExceptions.ErrorResponse;
import com.compassuol.sp.challenge.msproducts.controller.exception.getExceptions.ProductFetchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Endpoint GET
    @ExceptionHandler(ProductFetchException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleProductFetchException(ProductFetchException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getCode(), ex.getStatus(), ex.getMessage(), ex.getDetails());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    // Endpoint GET
}

