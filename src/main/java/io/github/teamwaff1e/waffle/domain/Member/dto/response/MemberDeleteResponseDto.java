package io.github.teamwaff1e.waffle.domain.Member.dto.response;

import io.github.teamwaff1e.waffle.domain.dto.ResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@Builder
public class MemberDeleteResponseDto implements ResponseDto {

    private String result;
    private boolean isSuccess;
    private int code;
    private String message;



}
