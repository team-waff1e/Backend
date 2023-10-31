package io.github.teamwaff1e.waffle.domain.member.controller;


import io.github.teamwaff1e.waffle.domain.member.dto.request.CreateMemberRequestDto;
import io.github.teamwaff1e.waffle.domain.member.dto.request.FollowRequestDto;
import io.github.teamwaff1e.waffle.domain.member.dto.request.UpdateMemberRequestDto;
import io.github.teamwaff1e.waffle.domain.member.dto.response.MemberResponseDto;
import io.github.teamwaff1e.waffle.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MemberResponseDto createMember(@ModelAttribute CreateMemberRequestDto memberRequestDto, BindingResult bindingResult) {
        return memberService.createMember(memberRequestDto);
    }

    @GetMapping("/{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public MemberResponseDto readMemberById(@PathVariable Long memberId) {
        return memberService.readMember(memberId);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public MemberResponseDto readMember() {
        return memberService.readMember(1L); //Todo : 임시 Id 변경해야 함
    }

    @PatchMapping()
    @ResponseStatus(HttpStatus.OK)
    public MemberResponseDto updateMember(@ModelAttribute UpdateMemberRequestDto memberRequestDto) {
        return memberService.updateMember(1L,memberRequestDto); //Todo : 임시 Id 변경해야 함
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMember(){
        memberService.deleteMember(1L);// Todo :: 임시
    }

    @PostMapping("/follow")
    @ResponseStatus(HttpStatus.OK)
    public void follow (@ModelAttribute FollowRequestDto followRequestDto){
    }
    @PostMapping("/unfollow")
    @ResponseStatus(HttpStatus.OK)
    public void unfollow (@ModelAttribute FollowRequestDto followRequestDto){
    }
    @GetMapping("/follow/{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public void readFollowById (@PathVariable Long memberd){

    }
}
