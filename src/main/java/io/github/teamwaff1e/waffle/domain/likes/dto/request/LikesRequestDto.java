package io.github.teamwaff1e.waffle.domain.likes.dto.request;

import io.github.teamwaff1e.waffle.global.dto.request.RequestDto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LikesRequestDto implements RequestDto {

    @NotBlank
    private Long memberId;

    @NotBlank
    private Long waffleId;
}
