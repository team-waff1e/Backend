package io.github.teamwaff1e.waffle.domain.member.dto.converter;

import io.github.teamwaff1e.waffle.domain.member.dto.response.MemberResponseDto;
import io.github.teamwaff1e.waffle.domain.member.entity.Member;
import io.github.teamwaff1e.waffle.domain.dto.converter.DtoConverter;

public class MemberToResponseConverter implements DtoConverter<Member, MemberResponseDto> {

    @Override
    public MemberResponseDto convert(Member member) {
        return MemberResponseDto.builder()
                .id(member.getId())
                .pwd(member.getPwd())
                .name(member.getName())
                .nickname(member.getNickname())
                .email(member.getEmail())
                .profileUrl((member.getProfileUrl()))
                .updatedAt(member.getUpdatedAt())
                .build();
    }
}
