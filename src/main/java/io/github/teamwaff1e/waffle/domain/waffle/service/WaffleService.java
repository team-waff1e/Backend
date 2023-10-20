package io.github.teamwaff1e.waffle.domain.waffle.service;

import io.github.teamwaff1e.waffle.domain.dto.converter.DtoConverter;
import io.github.teamwaff1e.waffle.domain.waffle.dto.request.CreateWaffleRequestDto;
import io.github.teamwaff1e.waffle.domain.waffle.dto.request.UpdateWaffleRequestDto;
import io.github.teamwaff1e.waffle.domain.waffle.dto.response.WaffleResponseDto;
import io.github.teamwaff1e.waffle.domain.waffle.entity.Waffle;
import io.github.teamwaff1e.waffle.domain.waffle.repository.WaffleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class WaffleService {

    private final WaffleRepository waffleRepository;
    private final DtoConverter<Waffle, WaffleResponseDto> converter;

    public WaffleResponseDto createWaffle(CreateWaffleRequestDto createWaffleRequestDto) {
        Waffle newWaffle = Waffle.builder()
                .content(createWaffleRequestDto.getContent())
                .memberId(createWaffleRequestDto.getMemberId())
                .build();

        Waffle waffle =  waffleRepository.save(newWaffle);
        return converter.convert(waffle);
    }

    public WaffleResponseDto updateWaffle(UpdateWaffleRequestDto updateWaffleRequestDto) {
        Waffle waffle = waffleRepository.update(updateWaffleRequestDto);
        return converter.convert(waffle);
    }

    public void deleteWaffle(Long waffleId) {
        waffleRepository.delete(waffleId);
    }

    public WaffleResponseDto likeWaffle(Long waffleId) {
        Waffle waffle = waffleRepository.likeById(waffleId);
        return converter.convert(waffle);
    }

    public WaffleResponseDto unlikeWaffle(Long waffleId) {
        Waffle waffle = waffleRepository.unlikeById(waffleId);
        return converter.convert(waffle);
    }

    public WaffleResponseDto readWaffle(Long waffleId) {
        Waffle waffle = waffleRepository.find(waffleId);
        return converter.convert(waffle);
    }
}
