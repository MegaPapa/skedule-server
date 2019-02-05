package com.megapapa.sk.auth.exception;

public class InvalidAuthTokenException extends RuntimeException {
    public InvalidAuthTokenException() {
        super();
    }

    public InvalidAuthTokenException(String message) {
        super(message);
    }

    public InvalidAuthTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidAuthTokenException(Throwable cause) {
        super(cause);
    }

    protected InvalidAuthTokenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
