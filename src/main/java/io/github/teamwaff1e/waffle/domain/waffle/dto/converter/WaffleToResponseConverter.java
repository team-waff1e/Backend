package io.github.teamwaff1e.waffle.domain.waffle.dto.converter;

import io.github.teamwaff1e.waffle.domain.dto.converter.DtoConverter;
import io.github.teamwaff1e.waffle.domain.waffle.dto.response.WaffleResponseDto;
import io.github.teamwaff1e.waffle.domain.waffle.entity.Waffle;

public class WaffleToResponseConverter implements DtoConverter<Waffle, WaffleResponseDto> {

    @Override
    public WaffleResponseDto convert(Waffle waffle) {
        return WaffleResponseDto.builder()
                .id(waffle.getId())
                .content(waffle.getContent())
                .memberId(waffle.getMemberId())
                .likes(waffle.getLikes())
                .build();
    }
}
