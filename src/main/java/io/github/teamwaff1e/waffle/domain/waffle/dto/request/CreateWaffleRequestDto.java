package io.github.teamwaff1e.waffle.domain.waffle.dto.request;

import io.github.teamwaff1e.waffle.global.dto.RequestDto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateWaffleRequestDto implements RequestDto {

    private Long memberId;
    @NotBlank
    private String content;

}
