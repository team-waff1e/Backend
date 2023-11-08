package io.github.teamwaff1e.waffle.domain.comment.dto.response;

import io.github.teamwaff1e.waffle.domain.member.dto.response.OwnerResponseDto;
import io.github.teamwaff1e.waffle.global.dto.response.ResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CommentResponseDto implements ResponseDto {

    private Long id;
    private Long waffleId;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private OwnerResponseDto ownerResponseDto;

}
