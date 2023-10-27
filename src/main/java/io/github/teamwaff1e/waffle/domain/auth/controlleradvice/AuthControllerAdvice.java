package io.github.teamwaff1e.waffle.domain.auth.controlleradvice;

import io.github.teamwaff1e.waffle.domain.auth.controller.AuthController;
import io.github.teamwaff1e.waffle.global.dto.response.ExceptionResponseDto;
import io.github.teamwaff1e.waffle.global.exception.auth.IllegalLoginStateException;
import io.github.teamwaff1e.waffle.global.exception.auth.LoginFailureException;
import io.github.teamwaff1e.waffle.global.exception.ErrorCode;
import io.github.teamwaff1e.waffle.global.exception.auth.MemberEmailAlreadyExistsException;
import io.github.teamwaff1e.waffle.global.exception.auth.MemberNicknameAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = AuthController.class)
public class AuthControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(LoginFailureException.class)
    public ExceptionResponseDto loginFailure(LoginFailureException loginFailureException) {
        return new ExceptionResponseDto(ErrorCode.LOGIN_FAILURE);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalLoginStateException.class)
    public ExceptionResponseDto logoutFailure(IllegalLoginStateException illegalLoginStateException) {
        return new ExceptionResponseDto(ErrorCode.LOGOUT_ILLEGAL_STATE);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MemberEmailAlreadyExistsException.class)
    public ExceptionResponseDto validateEmailFailure(MemberEmailAlreadyExistsException memberEmailAlreadyExistsException) {
        return new ExceptionResponseDto(ErrorCode.EMAIL_ALREADY_EXISTS);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MemberNicknameAlreadyExistsException.class)
    public ExceptionResponseDto validateNicknameFailure(MemberNicknameAlreadyExistsException memberNicknameAlreadyExistsException) {
        return new ExceptionResponseDto(ErrorCode.NICKNAME_ALREADY_EXISTS);
    }
}
