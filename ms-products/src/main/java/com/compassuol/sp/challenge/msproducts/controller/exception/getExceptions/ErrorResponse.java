package com.compassuol.sp.challenge.msproducts.controller.exception.getExceptions;

import lombok.Getter;

import java.util.List;
@Getter
public class ErrorResponse {
    private int code;
    private String status;
    private String message;
    private List<String> details;
    public ErrorResponse() {
    }

    public ErrorResponse(int code, String status, String message, List<String> details) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.details = details;
    }
}
