package com.compassuol.sp.challenge.msproducts.controller.exception;

import com.compassuol.sp.challenge.msproducts.controller.exception.errorTypes.BusinessErrorException;
import com.compassuol.sp.challenge.msproducts.controller.exception.errorTypes.ProductNameExistsException;
import com.compassuol.sp.challenge.msproducts.controller.exception.errorTypes.ProductNotFoundException;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {

    //exception for product not found
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> notFoundProductErrors(ProductNotFoundException ex) {
        final int error_code = HttpStatus.NOT_FOUND.value();
        final String status_code = HttpStatus.NOT_FOUND.toString();
        String message = ex.getMessage();

        var response = new ResponseErrorTemplate(error_code, status_code, message);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


    //exception for validation error
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> dataNotValidException(MethodArgumentNotValidException ex) {
        final int error_code = HttpStatus.BAD_REQUEST.value();
        final String status_code = HttpStatus.BAD_REQUEST.toString();
        String message = Objects.requireNonNull(ex.getFieldError()).getDefaultMessage();

        var response = new ResponseErrorTemplate(error_code, status_code, message);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    //exception for business violation
    @ExceptionHandler(BusinessErrorException.class)
    public ResponseEntity<Object> violatedBusinessConstraint(BusinessErrorException ex) {
        final int error_code = HttpStatus.BAD_REQUEST.value();
        final String status_code = HttpStatus.BAD_REQUEST.toString();
        String message = ex.getMessage();

        var response = new ResponseErrorTemplate(error_code, status_code, message);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    //exception for unexpected error
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException() {
        final int error_code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        final String status_code = HttpStatus.INTERNAL_SERVER_ERROR.toString();
        String message = "occurred an unexpected error";

        var response = new ResponseErrorTemplate(error_code, status_code, message);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProductNameExistsException.class)
    public ResponseEntity<Object> productNameExistsException(ProductNameExistsException ex) {
        final int error_code = HttpStatus.BAD_REQUEST.value();
        final String status_code = HttpStatus.BAD_REQUEST.toString();
        String message = ex.getMessage();

        var response = new ResponseErrorTemplate(error_code, status_code, message);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
