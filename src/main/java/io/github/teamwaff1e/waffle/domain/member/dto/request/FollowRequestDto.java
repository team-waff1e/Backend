package io.github.teamwaff1e.waffle.domain.member.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FollowRequestDto {
    @NotBlank
    private Long memberId;
}
