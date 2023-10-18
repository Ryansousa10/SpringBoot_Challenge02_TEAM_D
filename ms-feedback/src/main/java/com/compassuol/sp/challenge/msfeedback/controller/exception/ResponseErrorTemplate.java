package com.compassuol.sp.challenge.msfeedback.controller.exception;

import lombok.Getter;

@Getter
public class ResponseErrorTemplate {

    private int code;
    private String status;
    private String message;

    public ResponseErrorTemplate(int code, String status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
