package io.github.teamwaff1e.waffle.domain.waffle.controller;

import io.github.teamwaff1e.waffle.domain.auth.vo.AuthVo;
import io.github.teamwaff1e.waffle.domain.likes.dto.request.LikesRequestDto;
import io.github.teamwaff1e.waffle.domain.waffle.dto.request.CreateWaffleRequestDto;
import io.github.teamwaff1e.waffle.domain.waffle.dto.request.UpdateWaffleRequestDto;
import io.github.teamwaff1e.waffle.domain.waffle.dto.response.GetWaffleListResponseDto;
import io.github.teamwaff1e.waffle.domain.waffle.dto.response.WaffleResponseDto;
import io.github.teamwaff1e.waffle.domain.waffle.entity.Waffle;
import io.github.teamwaff1e.waffle.domain.waffle.service.WaffleService;
import io.github.teamwaff1e.waffle.domain.waffle.util.ScrollPaginationCollection;
import io.github.teamwaff1e.waffle.global.annotation.Login;
import io.github.teamwaff1e.waffle.global.exception.auth.IllegalLoginStateException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public GetWaffleListResponseDto readWaffleList(@Login AuthVo authVo,
                                                   @RequestParam Integer limit,  // size
                                                   @RequestParam Boolean isupdate,
                                                   @RequestParam Long idx) {  // lastFeedId

        // TODO requestDto 처리

        if(authVo == null) {
            // TODO 로그인 여부 분기
            throw new IllegalLoginStateException();
        }
        // TODO isupdate 처리

        PageRequest pageRequest = PageRequest.of(0, limit + 1);  // Pageable의 정보가 담겨 객체화 된 클래스
        return waffleService.readWaffleList(idx, pageRequest, limit, authVo.getMemberId());

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
