package com.compassuol.sp.challenge.msorders.controller.exception;

import com.compassuol.sp.challenge.msorders.controller.exception.errorTypes.OrderCancellationNotAllowedException;
import com.compassuol.sp.challenge.msorders.controller.exception.errorTypes.OrderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Object> handleOrderNotFoundException(OrderNotFoundException ex) {

        int error_code = HttpStatus.NOT_FOUND.value();
        String status_code = HttpStatus.NOT_FOUND.toString();
        String message = ex.getMessage();

        var response = new ResponseErrorTemplate(error_code, status_code, message);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderCancellationNotAllowedException.class)
    public ResponseEntity<Object> handleOrderCancellationNotAllowedException(OrderCancellationNotAllowedException ex) {

        int error_code = HttpStatus.BAD_REQUEST.value();
        String status_code = HttpStatus.BAD_REQUEST.toString();
        String message = ex.getMessage();

        var response = new ResponseErrorTemplate(error_code, status_code, message);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}

