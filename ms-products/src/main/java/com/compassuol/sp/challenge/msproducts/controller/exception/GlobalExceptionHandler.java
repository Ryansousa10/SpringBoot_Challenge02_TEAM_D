package com.compassuol.sp.challenge.msproducts.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> notFoundProductErrors(ProductNotFoundException ex) {
        final int error_code = HttpStatus.NOT_FOUND.value();
        final String status_code = HttpStatus.NOT_FOUND.toString();
        String message = ex.getMessage();

        var response = new ResponseErrorTemplate(error_code, status_code, message);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
