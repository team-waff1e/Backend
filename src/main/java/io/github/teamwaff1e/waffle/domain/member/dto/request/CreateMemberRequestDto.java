package io.github.teamwaff1e.waffle.domain.member.dto.request;

import io.github.teamwaff1e.waffle.global.dto.request.RequestDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateMemberRequestDto implements RequestDto {

    @NotBlank(message = "이메일 입력이 없습니다.")
    @Email( message = "이메일 형식이 맞지 않습니다.")
    private String email;
    @NotBlank(message = "이름 입력이 없습니다.")
    private String name;
    @NotBlank(message = "비밀번호 입력이 없습니다.")
    private String password;
    @NotBlank(message = "닉네임 입력이 없습니다.")
    private String nickname;
}
