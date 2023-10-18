package io.github.teamwaff1e.waffle.domain.comment.dto.converter;

import io.github.teamwaff1e.waffle.domain.comment.dto.response.CommentResponseDto;
import io.github.teamwaff1e.waffle.domain.comment.dto.response.MemberResponseDto;
import io.github.teamwaff1e.waffle.domain.comment.entity.Comment;
import io.github.teamwaff1e.waffle.domain.comment.entity.Member;
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
                .profile_url((member.getProfile_url()))
                .updatedAt(member.getUpdatedAt())
                .build();
    }
}
