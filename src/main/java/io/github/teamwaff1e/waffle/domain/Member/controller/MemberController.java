package io.github.teamwaff1e.waffle.domain.Member.controller;

import io.github.teamwaff1e.waffle.domain.Member.dto.request.CreateMemberRequestDto;
import io.github.teamwaff1e.waffle.domain.Member.dto.request.UpdateMemberRequestDto;
import io.github.teamwaff1e.waffle.domain.Member.dto.response.MemberDeleteResponseDto;
import io.github.teamwaff1e.waffle.domain.Member.dto.response.MemberResponseDto;
import io.github.teamwaff1e.waffle.domain.Member.entity.Member;
import io.github.teamwaff1e.waffle.domain.Member.sevice.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberServiced;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MemberResponseDto createMember(@ModelAttribute CreateMemberRequestDto memberRequestDto, BindingResult bindingResult) {
        return memberServiced.createMember(memberRequestDto);
    }

    @GetMapping("/{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public MemberResponseDto readMemberById(@PathVariable Long memberId) {
        return memberServiced.readMember(memberId);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public MemberResponseDto readMember() {
        return memberServiced.readMember(1L); //Todo : 임시 Id 변경해야 함
    }

    @PatchMapping()
    @ResponseStatus(HttpStatus.OK)
    public MemberResponseDto updateMember(@ModelAttribute UpdateMemberRequestDto memberRequestDto) {
        return memberServiced.updateMember(1L,memberRequestDto); //Todo : 임시 Id 변경해야 함
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    public MemberDeleteResponseDto deleteMember(){
        return memberServiced.deleteMember(1L);// Todo :: 임시
    }
}
