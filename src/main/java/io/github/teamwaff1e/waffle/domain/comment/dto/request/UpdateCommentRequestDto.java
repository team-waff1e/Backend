package io.github.teamwaff1e.waffle.domain.comment.dto.request;

import io.github.teamwaff1e.waffle.global.dto.request.RequestDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateCommentRequestDto implements RequestDto {

    @NotNull
    private Long waffleId;

    @NotNull
    private Long commentId;

    @NotBlank
    private String content;
}
