package io.github.teamwaff1e.waffle.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    LOGIN_FAILURE(1111), // todo
    LOGOUT_ILLEGAL_STATE(1112); // todo

    private final int code;
}
