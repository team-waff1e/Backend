package io.github.teamwaff1e.waffle.domain.waffle.dto.request;

import io.github.teamwaff1e.waffle.domain.dto.RequestDto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateWaffleRequestDto implements RequestDto {

    private Long waffleId;
    @NotBlank
    private String content;
}
