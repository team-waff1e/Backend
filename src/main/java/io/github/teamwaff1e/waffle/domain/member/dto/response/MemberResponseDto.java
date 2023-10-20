package io.github.teamwaff1e.waffle.domain.member.dto.response;

import io.github.teamwaff1e.waffle.global.dto.ResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@Builder
public class MemberResponseDto implements ResponseDto {

    private Long id;
    private String email;
    private String pwd;
    private String name;
    private String nickname;
    private String profileUrl;
    private Timestamp updatedAt;

}
