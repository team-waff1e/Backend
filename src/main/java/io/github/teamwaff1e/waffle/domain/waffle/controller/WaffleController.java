package io.github.teamwaff1e.waffle.domain.waffle.controller;

import io.github.teamwaff1e.waffle.domain.auth.vo.AuthVo;
import io.github.teamwaff1e.waffle.domain.likes.dto.request.LikesRequestDto;
import io.github.teamwaff1e.waffle.domain.waffle.dto.request.CreateWaffleRequestDto;
import io.github.teamwaff1e.waffle.domain.waffle.dto.request.UpdateWaffleRequestDto;
import io.github.teamwaff1e.waffle.domain.waffle.dto.response.WaffleResponseDto;
import io.github.teamwaff1e.waffle.domain.waffle.service.WaffleService;
import io.github.teamwaff1e.waffle.global.annotation.Login;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/waffles")
public class WaffleController {
    private final WaffleService waffleService;

    // TODO responseDto

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WaffleResponseDto createWaffle(@ModelAttribute CreateWaffleRequestDto createWaffleRequestDto, BindingResult bindingResult) {
        return waffleService.createWaffle(createWaffleRequestDto);
    }

    @GetMapping("/{waffleId}")
    @ResponseStatus(HttpStatus.OK)
    public WaffleResponseDto readWaffle(@PathVariable Long waffleId) {
        return waffleService.readWaffle(waffleId);
    }

    // TODO feed 구성에 대한 요구사항 정리
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<WaffleResponseDto> readWaffleList() {
//        return waffleService.readWaffleList();
        return null;
    }

    @PatchMapping("/{waffleId}")
    @ResponseStatus(HttpStatus.OK)
    public WaffleResponseDto updateWaffle(@ModelAttribute UpdateWaffleRequestDto updateWaffleRequestDto) {
        return waffleService.updateWaffle(updateWaffleRequestDto);
    }

    @DeleteMapping("/{waffleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWaffle(@PathVariable Long waffleId) {
        waffleService.deleteWaffle(waffleId);
    }

    // TODO like 요구사항 도출 후 수정
    @PostMapping("/{waffleId}/like")
    @ResponseStatus(HttpStatus.OK)
    public WaffleResponseDto likeWaffle(@PathVariable Long waffleId, @Login AuthVo authVo) {

        if(authVo == null) {
            // TODO 로그인페이지로 redirection
        }
        LikesRequestDto likesRequestDto = new LikesRequestDto(authVo.getMemberId(), waffleId);
        return waffleService.likeWaffle(likesRequestDto);
    }

    @PostMapping("/{waffleId}/unlike")
    @ResponseStatus(HttpStatus.OK)
    public WaffleResponseDto unlikeWaffle(@PathVariable Long waffleId, @Login AuthVo authVo) {

        if(authVo == null) {
            // TODO 예외처리
        }
        LikesRequestDto likesRequestDto = new LikesRequestDto(authVo.getMemberId(), waffleId);
        return waffleService.unlikeWaffle(likesRequestDto);
    }
}
