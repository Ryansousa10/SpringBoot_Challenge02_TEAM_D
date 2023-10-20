package com.compassuol.sp.challenge.msfeedback.controller.exception.errorTypes;

public class FeedbackNotFoundException extends RuntimeException{
    public FeedbackNotFoundException() {
    }

    public FeedbackNotFoundException(String message) {
        super(message);
    }

    public FeedbackNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public FeedbackNotFoundException(Throwable cause) {
        super(cause);
    }

    public FeedbackNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
