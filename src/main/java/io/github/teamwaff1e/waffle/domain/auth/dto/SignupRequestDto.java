package io.github.teamwaff1e.waffle.domain.auth.dto;

import io.github.teamwaff1e.waffle.global.dto.RequestDto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignupRequestDto implements RequestDto {
    @NotBlank
    private String email;
    @NotBlank
    private String name;
    @NotBlank
    private String pwd;
    @NotBlank
    private String nickname;
}
