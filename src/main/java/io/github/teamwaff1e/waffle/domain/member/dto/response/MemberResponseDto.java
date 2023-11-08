package io.github.teamwaff1e.waffle.domain.member.dto.response;

import io.github.teamwaff1e.waffle.global.dto.response.ResponseDto;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberResponseDto implements ResponseDto {

    private Long id;
    private String email;
    private String name;
    private String nickname;
    private String profileUrl;
    private LocalDateTime updatedAt;

}
