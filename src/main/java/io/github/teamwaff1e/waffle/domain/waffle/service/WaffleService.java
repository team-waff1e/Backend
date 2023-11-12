package io.github.teamwaff1e.waffle.domain.waffle.service;

import io.github.teamwaff1e.waffle.domain.auth.vo.AuthVo;
import io.github.teamwaff1e.waffle.domain.likes.dto.request.LikesRequestDto;
import io.github.teamwaff1e.waffle.domain.likes.entity.Likes;
import io.github.teamwaff1e.waffle.domain.likes.repository.LikesRepository;
import io.github.teamwaff1e.waffle.domain.member.entity.Follow;
import io.github.teamwaff1e.waffle.domain.member.entity.Member;
import io.github.teamwaff1e.waffle.domain.member.service.MemberService;
import io.github.teamwaff1e.waffle.domain.member.repository.MemberRepository;
import io.github.teamwaff1e.waffle.domain.waffle.dto.response.WaffleListResponseDto;
import io.github.teamwaff1e.waffle.global.dto.converter.DtoConverter;
import io.github.teamwaff1e.waffle.domain.waffle.dto.request.CreateWaffleRequestDto;
import io.github.teamwaff1e.waffle.domain.waffle.dto.request.UpdateWaffleRequestDto;
import io.github.teamwaff1e.waffle.domain.waffle.dto.response.WaffleResponseDto;
import io.github.teamwaff1e.waffle.domain.waffle.entity.Waffle;
import io.github.teamwaff1e.waffle.domain.waffle.repository.WaffleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public WaffleListResponseDto readWaffleList(Long lastWaffleIdx, Long loginMemberId, PageRequest pageRequest) {
        List<Follow> follows = memberService.readFollowById(loginMemberId);

        List<WaffleResponseDto> contents = waffleRepository.findByIdxLessThanAndFollowInOrderByIdDesc(lastWaffleIdx, follows, pageRequest)
                .map(converter::convert)
                .toList();

        Map<Long, Boolean> likesMap = new HashMap<>();
        for (WaffleResponseDto c : contents) {
            boolean isLiked = likesMap.computeIfAbsent(c.getId(), id -> likesRepository.findOneByMemberAndWaffleId(loginMemberId, id).isPresent());
            c.setIsLiked(isLiked);
        }

        int size = contents.size();
        Long lastIdx = contents.get(contents.size()-1).getId();
        boolean isLast = size < pageRequest.getPageSize();

        return WaffleListResponseDto.builder()
                .contents(contents)
                .lastIdx(lastIdx)
                .size(size)
                .isLast(isLast)
                .build();

    }

    public WaffleListResponseDto readWaffleListByMemberInOrderByIdDesc(Long lastWaffleIdx, Long memberId, PageRequest pageRequest) {
        List<WaffleResponseDto> contents = waffleRepository.findWaffleListByMemberIdInOrderByIdDesc(lastWaffleIdx, memberId, pageRequest)
                .map(converter::convert)
                .toList();

        int size = contents.size();
        Long lastIdx = contents.get(contents.size()-1).getId();
        boolean isLast = size < pageRequest.getPageSize();

        return WaffleListResponseDto.builder()
                .contents(contents)
                .lastIdx(lastIdx)
                .size(size)
                .isLast(isLast)
                .build();
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

    public WaffleResponseDto likeWaffle(LikesRequestDto likesRequestDto) {  // TODO 예외처리  // TODO DTO Service에서 까도록
        Member member = memberRepository.find(likesRequestDto.getMemberId());

        Waffle waffle = waffleRepository.find(likesRequestDto.getWaffleId());

        if(likesRepository.findOneByMemberAndWaffleId(member.getId(), waffle.getId()).isPresent()) {
            throw new IllegalArgumentException();
        }

        Waffle likedWaffle = waffleRepository.like(likesRequestDto);
        return converter.convert(likedWaffle);
    }

    public WaffleResponseDto unlikeWaffle(LikesRequestDto likesRequestDto) {  // TODO 예외처리  // TODO DTO Service에서 까도록
        Member member = memberRepository.find(likesRequestDto.getMemberId());

        Waffle waffle = waffleRepository.find(likesRequestDto.getWaffleId());

        Likes likes = likesRepository.findOneByMemberAndWaffleId(member.getId(), waffle.getId()).orElseThrow(IllegalArgumentException::new);

        Waffle unlikedWaffle = waffleRepository.unlike(likesRequestDto);
        return converter.convert(unlikedWaffle);
    }

}
