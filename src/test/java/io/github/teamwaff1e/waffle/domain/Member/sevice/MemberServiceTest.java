package io.github.teamwaff1e.waffle.domain.member.sevice;

import io.github.teamwaff1e.waffle.domain.member.dto.request.CreateMemberRequestDto;
import io.github.teamwaff1e.waffle.domain.member.dto.request.UpdateMemberRequestDto;
import io.github.teamwaff1e.waffle.domain.member.dto.response.MemberResponseDto;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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


}