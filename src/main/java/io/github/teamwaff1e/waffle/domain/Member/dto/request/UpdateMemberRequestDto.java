package io.github.teamwaff1e.waffle.domain.Member.dto.request;

import io.github.teamwaff1e.waffle.domain.dto.RequestDto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateMemberRequestDto implements RequestDto {
    @NotBlank
    private String nickname;
}
