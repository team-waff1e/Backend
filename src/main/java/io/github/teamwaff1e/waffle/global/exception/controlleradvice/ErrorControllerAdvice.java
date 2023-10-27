package io.github.teamwaff1e.waffle.global.exception.controlleradvice;

import io.github.teamwaff1e.waffle.global.dto.response.ExceptionResponseDto;
import io.github.teamwaff1e.waffle.global.exception.auth.WAuthenticationException;
import io.github.teamwaff1e.waffle.global.exception.code.ErrorCode;
import io.github.teamwaff1e.waffle.global.exception.controller.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = ErrorController.class)
public class ErrorControllerAdvice {

    @ExceptionHandler(WAuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionResponseDto unauthenticatedException(WAuthenticationException wAuthenticationException) {
        return new ExceptionResponseDto(ErrorCode.UNAUTHENTICATED);
    }
}
