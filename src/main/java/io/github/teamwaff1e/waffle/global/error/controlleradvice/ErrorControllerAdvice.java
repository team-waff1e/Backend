package io.github.teamwaff1e.waffle.global.error.controlleradvice;

import static io.github.teamwaff1e.waffle.global.error.code.ErrorCode.*;

import io.github.teamwaff1e.waffle.global.dto.response.ExceptionResponseDto;
import io.github.teamwaff1e.waffle.global.exception.auth.UnauthorizedException;
import io.github.teamwaff1e.waffle.global.exception.auth.WAuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = RestController.class)
public class ErrorControllerAdvice {

    @ExceptionHandler(WAuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionResponseDto unauthenticatedException(WAuthenticationException wAuthenticationException) {
        return new ExceptionResponseDto(UNAUTHENTICATED);
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionResponseDto unauthorizedException(UnauthorizedException unauthorizedException) {
        return new ExceptionResponseDto(UNAUTHORIZED);
    }
}
