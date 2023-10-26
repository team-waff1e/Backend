package io.github.teamwaff1e.waffle.global.exception.auth;

public class IllegalLoginStateException extends IllegalStateException { // todo: extends WAuthException?

    public IllegalLoginStateException() {
        super();
    }

    public IllegalLoginStateException(String s) {
        super(s);
    }

    public IllegalLoginStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalLoginStateException(Throwable cause) {
        super(cause);
    }
}
