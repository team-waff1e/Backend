package io.github.teamwaff1e.waffle.global.exception.auth;

public class WAuthenticationException extends WAuthException {

    public WAuthenticationException() {
        super();
    }

    public WAuthenticationException(String message) {
        super(message);
    }

    public WAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    public WAuthenticationException(Throwable cause) {
        super(cause);
    }

    protected WAuthenticationException(String message, Throwable cause, boolean enableSuppression,
                                       boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
