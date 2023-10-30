package io.github.teamwaff1e.waffle.global.exception.auth;

public class WAuthException extends RuntimeException {

    public WAuthException() {
        super();
    }

    public WAuthException(String message) {
        super(message);
    }

    public WAuthException(String message, Throwable cause) {
        super(message, cause);
    }

    public WAuthException(Throwable cause) {
        super(cause);
    }

    protected WAuthException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
