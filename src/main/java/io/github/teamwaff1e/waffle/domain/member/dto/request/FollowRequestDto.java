package io.github.teamwaff1e.waffle.domain.member.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FollowRequestDto {

    @NotNull(message = "팔로우ID가 없습니다.")
    private Long followingId;
}
