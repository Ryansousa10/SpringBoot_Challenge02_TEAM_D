package com.compassuol.sp.challenge.msfeedback.controller.exception.errorTypes;

public class BusinessErrorException extends RuntimeException{
    public BusinessErrorException() {
    }

    public BusinessErrorException(String message) {
        super(message);
    }

    public BusinessErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessErrorException(Throwable cause) {
        super(cause);
    }

    public BusinessErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
