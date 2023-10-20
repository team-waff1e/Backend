package io.github.teamwaff1e.waffle.domain.comment.dto.request;

import io.github.teamwaff1e.waffle.global.dto.RequestDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateCommentRequestDto implements RequestDto {

    @NotNull
    private Long waffleId;

    @NotBlank
    private String content;
}
