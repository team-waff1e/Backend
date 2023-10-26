package io.github.teamwaff1e.waffle.domain.auth.service;

import io.github.teamwaff1e.waffle.domain.auth.dto.request.LoginRequestDto;
import io.github.teamwaff1e.waffle.global.exception.auth.LoginFailureException;
import io.github.teamwaff1e.waffle.domain.auth.repository.AuthRepository;
import io.github.teamwaff1e.waffle.domain.auth.vo.AuthVo;
import io.github.teamwaff1e.waffle.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;

    @Transactional(readOnly = true)
    public AuthVo login(LoginRequestDto loginRequestDto) {
        Member member = authRepository.findValidMember(loginRequestDto.getEmail(), loginRequestDto.getPwd())
                .orElseThrow(LoginFailureException::new);

        return new AuthVo(member.getId());
    }
}
