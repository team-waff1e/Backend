package io.github.teamwaff1e.waffle.global.error.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    LOGIN_FAILURE(1111), // todo

    LOGOUT_ILLEGAL_STATE(1112), // todo

    EMAIL_ALREADY_EXISTS(1121),

    NICKNAME_ALREADY_EXISTS(1122),

    UNAUTHENTICATED(1115),

    UNAUTHORIZED(1116);


    private final int code;
}
