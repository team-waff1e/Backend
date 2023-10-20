package io.github.teamwaff1e.waffle.domain.waffle.dto.response;

import io.github.teamwaff1e.waffle.domain.dto.ResponseDto;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class WaffleResponseDto implements ResponseDto {

    private Long id;
    private Long memberId;
    private String content;
    private Long likes;

}
