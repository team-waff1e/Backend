package io.github.teamwaff1e.waffle.domain.waffle.controller;

import io.github.teamwaff1e.waffle.domain.waffle.dto.WaffleDto;
import io.github.teamwaff1e.waffle.domain.waffle.service.WaffleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/waffles")
public class WaffleController {
    private final WaffleService waffleService;

    // TODO responseDto

    @PostMapping
    public WaffleDto createWaffle(@ModelAttribute WaffleDto waffleDto) {
        return waffleService.createWaffle(waffleDto);
    }

    @GetMapping("/{waffleId}")
    public WaffleDto readWaffle(@PathVariable("waffleId") String waffleId) {
        return waffleService.readWaffle(waffleId);
    }

    @GetMapping
    public WaffleDto readWaffleList() {
        return waffleService.readWaffleList();
    }

    @PatchMapping("/{waffleId}")
    public WaffleDto updateWaffle(@ModelAttribute WaffleDto waffleDto, @PathVariable String waffleId) {
        return waffleService.updateWaffle(waffleDto);
    }

    @DeleteMapping("/{waffleId}")
    public String deleteWaffle(@PathVariable String waffleId) {
        return waffleService.deleteWaffle(waffleId);
    }

    @PostMapping("/{waffleId}/like")
    public WaffleDto likeWaffle(@PathVariable String waffleId) {
        return waffleService.likeWaffle(waffleId);
    }

    @PostMapping("/{waffleId}/unlike")
    public WaffleDto unlikeWaffle(@PathVariable String waffleId) {
        return waffleService.unlikeWaffle(waffleId);
    }
}
