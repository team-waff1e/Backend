package io.github.teamwaff1e.waffle.domain.comment.dto.request;

import io.github.teamwaff1e.waffle.domain.dto.RequestDto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateCommentRequestDto implements RequestDto {

    private Long commentId;

    @NotBlank
    private String content;
}
