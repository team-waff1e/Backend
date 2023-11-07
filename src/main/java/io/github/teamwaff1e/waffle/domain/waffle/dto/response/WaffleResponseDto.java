package io.github.teamwaff1e.waffle.domain.waffle.dto.response;

import io.github.teamwaff1e.waffle.global.dto.response.ResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class WaffleResponseDto implements ResponseDto {

    private Long id;
    private Long memberId;
    private String content;
    private Long likes_count;
    private Long comment_count;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
