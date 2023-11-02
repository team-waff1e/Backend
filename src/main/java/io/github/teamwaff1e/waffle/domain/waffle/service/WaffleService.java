package io.github.teamwaff1e.waffle.domain.waffle.service;

import io.github.teamwaff1e.waffle.domain.likes.dto.request.LikesRequestDto;
import io.github.teamwaff1e.waffle.domain.member.entity.Follow;
import io.github.teamwaff1e.waffle.domain.member.entity.Member;
import io.github.teamwaff1e.waffle.domain.member.service.MemberService;
import io.github.teamwaff1e.waffle.domain.waffle.dto.response.GetWaffleListResponseDto;
import io.github.teamwaff1e.waffle.domain.waffle.util.ScrollPaginationCollection;
import io.github.teamwaff1e.waffle.global.dto.converter.DtoConverter;
import io.github.teamwaff1e.waffle.domain.waffle.dto.request.CreateWaffleRequestDto;
import io.github.teamwaff1e.waffle.domain.waffle.dto.request.UpdateWaffleRequestDto;
import io.github.teamwaff1e.waffle.domain.waffle.dto.response.WaffleResponseDto;
import io.github.teamwaff1e.waffle.domain.waffle.entity.Waffle;
import io.github.teamwaff1e.waffle.domain.waffle.repository.WaffleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WaffleService {

    private final WaffleRepository waffleRepository;
    private final MemberService memberService;
    private final DtoConverter<Waffle, WaffleResponseDto> converter;

    public WaffleResponseDto createWaffle(CreateWaffleRequestDto createWaffleRequestDto) {
        Waffle newWaffle = Waffle.builder()
                .content(createWaffleRequestDto.getContent())
                .memberId(createWaffleRequestDto.getMemberId())
                .build();

        Waffle waffle =  waffleRepository.save(newWaffle);
        return converter.convert(waffle);
    }

    public WaffleResponseDto readWaffle(Long waffleId) {
        Waffle waffle = waffleRepository.find(waffleId);
        return converter.convert(waffle);
    }
    public GetWaffleListResponseDto readWaffleList(Long idx, PageRequest pageRequest, Integer limit, Long loginMemberId) {
        List<Follow> follows = memberService.readFollowById(loginMemberId);
        Page<Waffle> page = waffleRepository.findByIdxLessThanAndMemberInOrderByIdDesc(idx, follows, pageRequest);

        List<Waffle> waffleList = page.getContent();
        ScrollPaginationCollection<Waffle> feedsCursor = ScrollPaginationCollection.of(waffleList, limit);

        return GetWaffleListResponseDto.of(feedsCursor, waffleList.size());
    }

    public WaffleResponseDto updateWaffle(UpdateWaffleRequestDto updateWaffleRequestDto) {
        Waffle waffle = waffleRepository.update(updateWaffleRequestDto);
        return converter.convert(waffle);
    }

    public void deleteWaffle(Long waffleId) {
        waffleRepository.delete(waffleId);
    }

    public WaffleResponseDto likeWaffle(LikesRequestDto likesRequestDto) {
        if(waffleRepository.isLiked(likesRequestDto)) {
            waffleRepository.unlike(likesRequestDto);
            // TODO return
        }
        Waffle waffle = waffleRepository.like(likesRequestDto);
        return converter.convert(waffle);
    }

    public WaffleResponseDto unlikeWaffle(LikesRequestDto likesRequestDto) {
        if(!waffleRepository.isLiked(likesRequestDto)) {
            waffleRepository.like(likesRequestDto);
            // TODO return
        }
        Waffle waffle = waffleRepository.unlike(likesRequestDto);
        return converter.convert(waffle);
    }
}
