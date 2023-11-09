package io.github.teamwaff1e.waffle.global.dto.response;

import io.github.teamwaff1e.waffle.global.error.code.ErrorCode;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class ExceptionResponseDto implements ResponseDto {

    private final int errorCode;
    private final String errorMessage;
    private final List<String> errorDetails;

    public ExceptionResponseDto(ErrorCode errorCode) {
        this(errorCode, new ArrayList<>()); // todo: null or empty list?
    }

    public ExceptionResponseDto(ErrorCode errorCode, List<String> details) {
        this.errorCode = errorCode.getCode();
        this.errorMessage = errorCode.getMessage();
        this.errorDetails = details;
    }
}
