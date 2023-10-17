package io.github.teamwaff1e.waffle.domain.comment.dto.response;

import io.github.teamwaff1e.waffle.domain.dto.ResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@Builder
public class CommentResponseDto implements ResponseDto {

    private Long id;
    private Long waffleId;
    private String content;
    private Timestamp updatedAt;

}
