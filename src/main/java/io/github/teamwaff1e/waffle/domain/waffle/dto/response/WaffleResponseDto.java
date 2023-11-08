package io.github.teamwaff1e.waffle.domain.waffle.dto.response;

import io.github.teamwaff1e.waffle.domain.member.dto.response.OwnerResponseDto;
import io.github.teamwaff1e.waffle.global.dto.response.ResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class WaffleResponseDto implements ResponseDto {

    private Long id;
    private String content;
    private Long likesCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private OwnerResponseDto owner;

}
