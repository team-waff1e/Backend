package io.github.teamwaff1e.waffle.domain.member.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OwnerResponseDto {

    private Long id;
    private String nickname;
    private String profile_url;
}
