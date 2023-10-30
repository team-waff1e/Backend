package io.github.teamwaff1e.waffle.domain.auth.dto;

import io.github.teamwaff1e.waffle.global.dto.request.RequestDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ValidateEmailRequestDto implements RequestDto {
    @NotBlank
    @Email
    private String email;
}
