package io.github.teamwaff1e.waffle.global.dto.response;

import io.github.teamwaff1e.waffle.global.error.code.ErrorCode;
import lombok.Getter;

@Getter
public class ExceptionResponseDto implements ResponseDto {

    private final int errorCode;

    public ExceptionResponseDto(ErrorCode errorCode) {
        this.errorCode = errorCode.getCode();
    }
}
