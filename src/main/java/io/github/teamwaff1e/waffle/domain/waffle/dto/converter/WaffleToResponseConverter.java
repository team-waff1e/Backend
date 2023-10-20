package io.github.teamwaff1e.waffle.domain.waffle.dto.converter;

import io.github.teamwaff1e.waffle.global.dto.converter.DtoConverter;
import io.github.teamwaff1e.waffle.domain.waffle.dto.response.WaffleResponseDto;
import io.github.teamwaff1e.waffle.domain.waffle.entity.Waffle;

public class WaffleToResponseConverter implements DtoConverter<Waffle, WaffleResponseDto> {

    @Override
    public WaffleResponseDto convert(Waffle waffle) {
        return WaffleResponseDto.builder()
                .id(waffle.getId())
                .memberId(waffle.getMemberId())
                .content(waffle.getContent())
                .likes(waffle.getLikes())
                .createdAt(waffle.getCreatedAt())
                .updatedAt(waffle.getUpdatedAt())
                .build();
    }
}
