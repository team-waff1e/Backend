package io.github.teamwaff1e.waffle.domain.waffle.controller;

import io.github.teamwaff1e.waffle.domain.auth.vo.AuthVo;
import io.github.teamwaff1e.waffle.domain.likes.dto.request.LikesRequestDto;
import io.github.teamwaff1e.waffle.domain.waffle.dto.request.CreateWaffleRequestDto;
import io.github.teamwaff1e.waffle.domain.waffle.dto.request.UpdateWaffleRequestDto;
import io.github.teamwaff1e.waffle.domain.waffle.dto.response.GetWaffleListResponseDto;
import io.github.teamwaff1e.waffle.domain.waffle.dto.response.WaffleResponseDto;
import io.github.teamwaff1e.waffle.domain.waffle.service.WaffleService;
import io.github.teamwaff1e.waffle.global.annotation.Login;
import io.github.teamwaff1e.waffle.global.exception.auth.IllegalLoginStateException;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/waffles")
public class WaffleController {

    private final WaffleService waffleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WaffleResponseDto createWaffle(@Login AuthVo authVo, @Validated @RequestBody CreateWaffleRequestDto createWaffleRequestDto) {

        if(authVo == null) {
            throw new IllegalLoginStateException();
        }
        return waffleService.createWaffle(authVo, createWaffleRequestDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public GetWaffleListResponseDto readWaffleList(@Login AuthVo authVo,
                                                   @RequestParam Integer limit,  // size
                                                   @RequestParam Long idx) {  // lastFeedId

        if(authVo == null) {
            // TODO 로그인 여부 분기
            throw new IllegalLoginStateException();  // TODO 로그인 안한 경우 보여주기로 한 데이터 처리 api
        }

        PageRequest pageRequest = PageRequest.of(0, limit + 1);  // Pageable의 정보가 담겨 객체화 된 클래스
        return waffleService.readWaffleList(idx, pageRequest, limit, authVo.getMemberId());

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public GetWaffleListResponseDto readWaffleListByMemberId(@RequestParam Long memberId,
                                                             @RequestParam Integer limit,
                                                             @RequestParam Long idx) {

        PageRequest pageRequest = PageRequest.of(0, limit + 1);
        return waffleService.readWaffleListByMemberInOrderByIdDesc(idx, pageRequest, limit, memberId);
    }
    @GetMapping("/{waffleId}")
    @ResponseStatus(HttpStatus.OK)
    public WaffleResponseDto readWaffle(@PathVariable @NotNull @Positive Long waffleId) {
        return waffleService.readWaffle(waffleId);
    }

    @PatchMapping("/{waffleId}")
    @ResponseStatus(HttpStatus.OK)
    public WaffleResponseDto updateWaffle(@Login AuthVo authVo, @PathVariable @NotNull @Positive Long waffleId,
                                       @Validated @RequestBody UpdateWaffleRequestDto updateWaffleRequestDto) throws IllegalAccessException {

        if(authVo == null) {
            throw new IllegalLoginStateException();  // TODO loginpage로 가도록 예외처리
        }
        // TODO 자기게시물 아닌경우 예외처리
        return waffleService.updateWaffle(waffleId, authVo.getMemberId(), updateWaffleRequestDto);

    }

    @DeleteMapping("/{waffleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWaffle(@PathVariable @NotNull @Positive Long waffleId) {
        waffleService.deleteWaffle(waffleId);
    }

    @PostMapping("/{waffleId}/like")
    @ResponseStatus(HttpStatus.OK)
    public WaffleResponseDto likeWaffle(@PathVariable @NotNull @Positive Long waffleId, @Login AuthVo authVo) {

        if(authVo == null) {
            // TODO 예외처리
            throw new IllegalLoginStateException();
        }
        LikesRequestDto likesRequestDto = new LikesRequestDto(authVo.getMemberId(), waffleId);
        return waffleService.likeWaffle(likesRequestDto);
    }

    @PostMapping("/{waffleId}/unlike")
    @ResponseStatus(HttpStatus.OK)
    public WaffleResponseDto unlikeWaffle(@PathVariable @NotNull @Positive Long waffleId, @Login AuthVo authVo) {

        if(authVo == null) {
            // TODO 예외처리
            throw new IllegalLoginStateException();
        }
        LikesRequestDto likesRequestDto = new LikesRequestDto(authVo.getMemberId(), waffleId);
        return waffleService.unlikeWaffle(likesRequestDto);
    }
}
