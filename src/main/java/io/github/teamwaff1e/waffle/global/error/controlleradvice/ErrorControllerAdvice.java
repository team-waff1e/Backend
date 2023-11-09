package io.github.teamwaff1e.waffle.global.error.controlleradvice;

import static io.github.teamwaff1e.waffle.global.error.code.ErrorCode.*;

import io.github.teamwaff1e.waffle.global.dto.response.ExceptionResponseDto;
import io.github.teamwaff1e.waffle.global.exception.auth.UnauthorizedException;
import io.github.teamwaff1e.waffle.global.exception.auth.WAuthenticationException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RequiredArgsConstructor
@RestControllerAdvice(annotations = RestController.class)
public class ErrorControllerAdvice {

    private final MessageSource messageSource;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponseDto methodArgumentNotValidException(
            MethodArgumentNotValidException methodArgumentNotValidException) {

        List<String> fieldErrorMessages = getFieldErrorMessages(methodArgumentNotValidException.getBindingResult());
        return new ExceptionResponseDto(INVALID_ARGUMENT, fieldErrorMessages);
    }

    private List<String> getFieldErrorMessages(Errors errors) {
        return errors.getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + " " + messageSource.getMessage(fieldError.getCode(),
                        null, fieldError.getDefaultMessage(), null))
                .collect(Collectors.toList());
    }
}
