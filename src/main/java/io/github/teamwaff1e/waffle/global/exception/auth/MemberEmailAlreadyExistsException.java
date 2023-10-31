package io.github.teamwaff1e.waffle.global.exception.auth;


public class MemberEmailAlreadyExistsException extends RuntimeException {
    public MemberEmailAlreadyExistsException() {
        super();
    }

    public MemberEmailAlreadyExistsException(String message) {
        super(message);
    }

    public MemberEmailAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public MemberEmailAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    protected MemberEmailAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
