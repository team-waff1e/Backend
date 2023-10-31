package io.github.teamwaff1e.waffle.domain.member.controller;


import io.github.teamwaff1e.waffle.domain.member.dto.request.CreateMemberRequestDto;
import io.github.teamwaff1e.waffle.domain.member.dto.request.FollowRequestDto;
import io.github.teamwaff1e.waffle.domain.member.dto.request.UpdateMemberRequestDto;
import io.github.teamwaff1e.waffle.domain.member.dto.response.MemberResponseDto;
import io.github.teamwaff1e.waffle.domain.member.service.MemberService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity createMember(@Validated @ModelAttribute CreateMemberRequestDto memberRequestDto,
                                          BindingResult bindingResult) {


        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors().stream().map(v->{
                Map<String,String> map = new HashMap<>();
                map.put("errorCode",v.getCode());
                map.put("message",v.getDefaultMessage());
                return map;
            }));
        }
        System.out.println(22222);
        return ResponseEntity.ok(memberService.createMember(memberRequestDto));
//        return memberService.createMember(memberRequestDto);
    }

    @GetMapping("/{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public MemberResponseDto readMemberById(@PathVariable Long memberId) {
        System.out.println("!@#DAS");
        return memberService.readMember(memberId);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public MemberResponseDto readMember() {
        return memberService.readMember(5L); //Todo : 임시 Id 변경해야 함
    }

    @PatchMapping()
    @ResponseStatus(HttpStatus.OK)
    public MemberResponseDto updateMember(@Validated @ModelAttribute UpdateMemberRequestDto memberRequestDto) {
        return memberService.updateMember(1L,memberRequestDto); //Todo : 임시 Id 변경해야 함
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMember(){
        memberService.deleteMember(1L);// Todo :: 임시
    }

    @PostMapping("/follow")
    @ResponseStatus(HttpStatus.OK)
    public void follow (@Validated @ModelAttribute FollowRequestDto followRequestDto){
    }
    @PostMapping("/unfollow")
    @ResponseStatus(HttpStatus.OK)
    public void unfollow (@Validated @ModelAttribute FollowRequestDto followRequestDto){
    }
    @GetMapping("/follow/{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public void readFollowById (@NotEmpty @Positive @PathVariable Long memberd){

    }
}
