package io.github.teamwaff1e.waffle.domain.Member.sevice;

import io.github.teamwaff1e.waffle.domain.Member.dto.request.CreateMemberRequestDto;
import io.github.teamwaff1e.waffle.domain.Member.dto.request.UpdateMemberRequestDto;
import io.github.teamwaff1e.waffle.domain.Member.dto.response.MemberDeleteResponseDto;
import io.github.teamwaff1e.waffle.domain.Member.dto.response.MemberResponseDto;
import io.github.teamwaff1e.waffle.domain.Member.entity.Member;
import io.github.teamwaff1e.waffle.domain.dto.converter.DtoConverter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
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

        MemberDeleteResponseDto deleteResponseDto = memberService.deleteMember(newMember.getId());


        Assertions.assertThat(deleteResponseDto.isSuccess()).isEqualTo(true);
    }


}