package io.github.teamwaff1e.waffle.global.exception.auth;

public class MemberNicknameAlreadyExistsException extends RuntimeException {
    public MemberNicknameAlreadyExistsException() {
        super();
    }

    public MemberNicknameAlreadyExistsException(String message) {
        super(message);
    }

    public MemberNicknameAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public MemberNicknameAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    protected MemberNicknameAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
