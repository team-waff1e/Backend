package io.github.teamwaff1e.waffle.domain.member.dto.request;

import io.github.teamwaff1e.waffle.global.dto.request.RequestDto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateMemberRequestDto implements RequestDto {
    @NotBlank(message = "닉네임 데이터가 없습니다.")
    private String nickname;
}
