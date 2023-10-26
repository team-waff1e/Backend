package io.github.teamwaff1e.waffle.domain.member.service;

import io.github.teamwaff1e.waffle.domain.member.dto.request.CreateMemberRequestDto;
import io.github.teamwaff1e.waffle.domain.member.dto.request.UpdateMemberRequestDto;
import io.github.teamwaff1e.waffle.domain.member.dto.response.MemberResponseDto;
import io.github.teamwaff1e.waffle.domain.member.entity.Follow;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;



    @Test
    void MemberCRUD() {
        CreateMemberRequestDto newMemberDto =
                new CreateMemberRequestDto(
                        "tester@test.com",
                        "tester",
                        "testing",
                        "tester");

        MemberResponseDto newMember = memberService.createMember(newMemberDto);

        MemberResponseDto readMember = memberService.readMember(newMember.getId());

        Assertions.assertThat(readMember.getId()).isEqualTo(newMember.getId());


        String tempNick = "닉네임 변경 테스트" + Integer.toString((int) (Math.random() * 100));
        UpdateMemberRequestDto updateMemberRequestDto =
                new UpdateMemberRequestDto(tempNick);

        MemberResponseDto updateMember = memberService.updateMember(newMember.getId(),updateMemberRequestDto);


        Assertions.assertThat(updateMember.getNickname()).isEqualTo(tempNick);

        boolean deleteResponseDto = memberService.deleteMember(newMember.getId());


        Assertions.assertThat(deleteResponseDto).isEqualTo(true);
    }

    @Test
    void followTest(){
        memberService.follow(2L);
        memberService.follow(3L);
        memberService.follow(8L);

        List<Follow> list = memberService.readFollowById(5L);
//        for(Follow f : list) System.out.println(f);
        Assertions.assertThat(list.size()).isEqualTo(3);
        memberService.unfollow(5L,8L);
        list = memberService.readFollowById(5L);
//        for(Follow f : list) System.out.println(f);


        Assertions.assertThat(list.size()).isEqualTo(2);
    }
}