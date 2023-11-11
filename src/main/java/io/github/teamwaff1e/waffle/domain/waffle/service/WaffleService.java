package io.github.teamwaff1e.waffle.domain.waffle.service;

import io.github.teamwaff1e.waffle.domain.auth.vo.AuthVo;
import io.github.teamwaff1e.waffle.domain.likes.dto.request.LikesRequestDto;
import io.github.teamwaff1e.waffle.domain.likes.entity.Likes;
import io.github.teamwaff1e.waffle.domain.likes.repository.LikesRepository;
import io.github.teamwaff1e.waffle.domain.member.entity.Follow;
import io.github.teamwaff1e.waffle.domain.member.entity.Member;
import io.github.teamwaff1e.waffle.domain.member.service.MemberService;
import io.github.teamwaff1e.waffle.domain.member.repository.MemberRepository;
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
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class WaffleService {

    private final MemberService memberService;
    private final WaffleRepository waffleRepository;
    private final MemberRepository memberRepository;
    private final LikesRepository likesRepository;

    private final DtoConverter<Waffle, WaffleResponseDto> converter;

    public WaffleResponseDto createWaffle(AuthVo authVo, CreateWaffleRequestDto createWaffleRequestDto) {

        Member member = memberRepository.find(authVo.getMemberId());

        Waffle newWaffle = Waffle.builder()
                .content(createWaffleRequestDto.getContent())
                .member(member)
                .build();

        Waffle waffle =  waffleRepository.save(newWaffle);
        WaffleResponseDto waffleResponseDto = converter.convert(waffle);
        return waffleResponseDto.setDefaultValue(waffleResponseDto);
    }

    public WaffleResponseDto readWaffle(Long waffleId) {
        Waffle waffle = waffleRepository.find(waffleId);
        return converter.convert(waffle);
    }
    public GetWaffleListResponseDto readWaffleList(Long idx, PageRequest pageRequest, Integer limit, Long loginMemberId) {
        List<Follow> follows = memberService.readFollowById(loginMemberId);
        Page<Waffle> page = waffleRepository.findByIdxLessThanAndFollowInOrderByIdDesc(idx, follows, pageRequest);

        List<Waffle> waffleList = page.getContent();
        ScrollPaginationCollection<Waffle> feedsCursor = ScrollPaginationCollection.of(waffleList, limit);

        return GetWaffleListResponseDto.of(feedsCursor, waffleList.size());
    }

    public GetWaffleListResponseDto readWaffleListByMemberInOrderByIdDesc(Long idx, PageRequest pageRequest, Integer limit, Long memberId) {
        Page<Waffle> page = waffleRepository.findWaffleListByMemberIdInOrderByIdDesc(idx, memberId, pageRequest);
        List<Waffle> waffleList = page.getContent();
        ScrollPaginationCollection<Waffle> feedCursur = ScrollPaginationCollection.of(waffleList, limit);

        return GetWaffleListResponseDto.of(feedCursur, waffleList.size());

    }

    public WaffleResponseDto updateWaffle(Long waffleId, Long memberId, UpdateWaffleRequestDto updateWaffleRequestDto) throws IllegalAccessException {
        Optional<Waffle> waffle = waffleRepository.findWaffleByWaffleIdAndMemberId(waffleId, memberId);
        if(waffle.isPresent()) {
            Waffle updatedWaffle = waffleRepository.update(waffleId, updateWaffleRequestDto);
            return converter.convert(updatedWaffle);
        } else throw new IllegalAccessException();
        // TODO 자기게시물 아닌경우 예외처리

    }

    public void deleteWaffle(Long waffleId) {
        waffleRepository.delete(waffleId);
    }

    public WaffleResponseDto likeWaffle(LikesRequestDto likesRequestDto) {  // TODO 예외처리
        Member member = memberRepository.find(likesRequestDto.getMemberId());

        Waffle waffle = waffleRepository.find(likesRequestDto.getWaffleId());

        if(likesRepository.find(likesRequestDto).isPresent()) {
            throw new IllegalArgumentException();
        }

        Waffle likedWaffle = waffleRepository.like(likesRequestDto);
        return converter.convert(likedWaffle);
    }

    public WaffleResponseDto unlikeWaffle(LikesRequestDto likesRequestDto) {  // TODO 예외처리
        Member member = memberRepository.find(likesRequestDto.getMemberId());

        Waffle waffle = waffleRepository.find(likesRequestDto.getWaffleId());

        Likes likes = likesRepository.find(likesRequestDto).orElseThrow(IllegalArgumentException::new);

        Waffle unlikedWaffle = waffleRepository.unlike(likesRequestDto);
        return converter.convert(unlikedWaffle);
    }
}
