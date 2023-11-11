package io.github.teamwaff1e.waffle.domain.waffle.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class WaffleListResponseDto {

    private List<WaffleResponseDto> contents;
    private Long lastIdx;
    private Integer size;
//    private boolean isLast;
}
