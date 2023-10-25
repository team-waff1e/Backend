package io.github.teamwaff1e.waffle.domain.auth.dto;

import io.github.teamwaff1e.waffle.global.dto.RequestDto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ValidateEmailRequestDto implements RequestDto {
    @NotBlank
    private String email;
}
