package io.github.teamwaff1e.waffle.domain.member.sevice;

import io.github.teamwaff1e.waffle.domain.member.dto.request.CreateMemberRequestDto;
import io.github.teamwaff1e.waffle.domain.member.dto.request.UpdateMemberRequestDto;
import io.github.teamwaff1e.waffle.domain.member.dto.response.MemberResponseDto;
import io.github.teamwaff1e.waffle.domain.member.entity.Member;
import io.github.teamwaff1e.waffle.domain.member.repository.MemberRepository;
import io.github.teamwaff1e.waffle.domain.dto.converter.DtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final DtoConverter<Member, MemberResponseDto> converter;
    private final MemberRepository memberRepository;
    public MemberResponseDto createMember(CreateMemberRequestDto memberRequestDto) {
        Member newMember = Member.builder()
                .name(memberRequestDto.getName())
                .email(memberRequestDto.getEmail())
                .pwd(memberRequestDto.getPassword())
                .nickname(memberRequestDto.getNickname())
                .build();

        Member member = memberRepository.save(newMember);

        return converter.convert(member);
    }

    public MemberResponseDto readMember(Long memberId){
        Member member= memberRepository.find(memberId);
        return converter.convert(member);
    }

    public MemberResponseDto updateMember(long memberId,UpdateMemberRequestDto memberRequestDto){
        Member member= memberRepository.update(memberId,memberRequestDto);

        return converter.convert((member));
    }

    public boolean deleteMember(Long memberId){
        memberRepository.delete(memberId);



        return true;
    }
}
