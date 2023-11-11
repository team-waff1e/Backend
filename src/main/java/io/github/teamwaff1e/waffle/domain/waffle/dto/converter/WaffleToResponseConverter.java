package io.github.teamwaff1e.waffle.domain.waffle.dto.converter;

import io.github.teamwaff1e.waffle.domain.member.dto.response.OwnerResponseDto;
import io.github.teamwaff1e.waffle.domain.member.entity.Member;
import io.github.teamwaff1e.waffle.global.dto.converter.DtoConverter;
import io.github.teamwaff1e.waffle.domain.waffle.dto.response.WaffleResponseDto;
import io.github.teamwaff1e.waffle.domain.waffle.entity.Waffle;

public class WaffleToResponseConverter implements DtoConverter<Waffle, WaffleResponseDto> {

    @Override
    public WaffleResponseDto convert(Waffle waffle) {
        Member member = waffle.getMember();

        OwnerResponseDto ownerResponseDto = OwnerResponseDto.builder()
                .id(member.getId())
                .nickname(member.getNickname())
                .profile_url(member.getProfileUrl())
                .build();

        return WaffleResponseDto.builder()
                .id(waffle.getId())
                .content(waffle.getContent())
                .likesCount(waffle.getLikesCount())
                .commentCount(waffle.getCommentCount())
                .createdAt(waffle.getCreatedAt())
                .updatedAt(waffle.getUpdatedAt())
                .owner(ownerResponseDto)
                .build();
    }
}
