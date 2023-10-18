package io.github.teamwaff1e.waffle.domain.waffle.service;

import io.github.teamwaff1e.waffle.domain.waffle.dto.WaffleDto;
import io.github.teamwaff1e.waffle.domain.waffle.entity.Waffle;
import io.github.teamwaff1e.waffle.domain.waffle.repository.WaffleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WaffleService {

    private final WaffleRepository waffleRepository;

    private WaffleDto convertEntityToDto(Waffle waffle) {
        return WaffleDto.builder()
                .id(waffle.getId())
                .content(waffle.getContent())
                .createdAt(waffle.getCreatedAt().toString())
                .updatedAt(waffle.getUpdatedAt().toString())
                .likes(waffle.getLikes())
                .memberId(waffle.getMemberId())
                .build();
    }

    public WaffleService(WaffleRepository waffleRepository) {
        this.waffleRepository = waffleRepository;
    }

    public WaffleDto createWaffle(WaffleDto waffleDto) {
        Waffle waffle = waffleDto.toEntity();
        return waffleRepository.save(waffle);
    }

    public WaffleDto updateWaffle(WaffleDto waffleDto) {
        Waffle waffle = waffleDto.toEntity();
        return waffleRepository.update(waffle);
    }

    public String deleteWaffle(String waffleId) {
        return waffleRepository.deleteById(waffleId);
    }

    public WaffleDto likeWaffle(String waffleId) {
//        waffleRepository.likeById(waffleId);
    }

    public WaffleDto unlikeWaffle(String waffleId) {
//        return waffleRepository.unlikeById(waffleId);
    }

    public WaffleDto readWaffle(String waffleId) {
        Optional<Waffle> waffleWrapper = waffleRepository.findById(waffleId);
        Waffle waffle = waffleWrapper.get();

        WaffleDto waffleDto = WaffleDto.builder()
                .id(waffle.getId())
                .content(waffle.getContent())
                .createdAt(waffle.getCreatedAt().toString())
                .updatedAt(waffle.getUpdatedAt().toString())
                .likes(waffle.getLikes())
                .memberId(waffle.getMemberId())
                .build();
        return waffleDto;
    }
}
