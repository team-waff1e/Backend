package io.github.teamwaff1e.waffle.domain.auth.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import io.github.teamwaff1e.waffle.domain.auth.dto.request.LoginRequestDto;
import io.github.teamwaff1e.waffle.global.exception.auth.LoginFailureException;
import io.github.teamwaff1e.waffle.domain.auth.vo.AuthVo;
import io.github.teamwaff1e.waffle.domain.member.dao.MemberDao;
import io.github.teamwaff1e.waffle.domain.member.entity.Member;
import java.util.NoSuchElementException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@SpringBootTest
class AuthServiceTest {

    @Autowired
    AuthService authService;
    @Autowired
    MemberDao memberDao;

    private static Long id;

    @BeforeEach
    void beforeEach() {
        Member member = Member.builder()
                .email("testOnly@waffle.com")
                .pwd("1234")
                .name("tester")
                .nickname("tester")
                .build();

        Member savedMember = memberDao.save(member);
        id = savedMember.getId();

        log.info("saved member id={}", id);
    }

    @Test
    @DisplayName("로그인 서비스 성공 테스트")
    void loginSuccess() {
        Long memberId = id;

        Member member = memberDao.findById(memberId).orElseThrow(NoSuchElementException::new);

        AuthVo authVo = authService.login(new LoginRequestDto(member.getEmail(), member.getPwd()));
        assertThat(authVo.getMemberId()).isEqualTo(member.getId());
    }

    @Test
    @DisplayName("로그인 서비스 실패 테스트 - 잘못된 이메일")
    void loginFailureWrongEmail() {
        Long memberId = id;
        String wrongEmail = "wrong_admin@waffle.com";

        Member member = memberDao.findById(memberId).orElseThrow(NoSuchElementException::new);

        assertThatThrownBy(() -> authService.login(new LoginRequestDto(wrongEmail, member.getPwd())))
                .isInstanceOf(LoginFailureException.class);
    }

    @Test
    @DisplayName("로그인 서비스 실패 테스트 - 잘못된 비밀번호")
    void loginFailureWrongPassword() {
        Long memberId = id;
        String wrongPwd = "wrong_1234"; // wrong password

        Member member = memberDao.findById(memberId).orElseThrow(NoSuchElementException::new);

        assertThatThrownBy(() -> authService.login(new LoginRequestDto(member.getEmail(), wrongPwd)))
                .isInstanceOf(LoginFailureException.class);
    }
}