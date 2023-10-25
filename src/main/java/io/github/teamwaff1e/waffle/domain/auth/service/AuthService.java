package io.github.teamwaff1e.waffle.domain.auth.service;

import io.github.teamwaff1e.waffle.domain.auth.dto.SignupRequestDto;
import io.github.teamwaff1e.waffle.domain.auth.exception.MemberEmailAlreadyExistsException;
import io.github.teamwaff1e.waffle.domain.auth.exception.MemberNicknameAlreadyExistsException;
import io.github.teamwaff1e.waffle.domain.auth.repository.AuthRepository;
import io.github.teamwaff1e.waffle.domain.member.entity.Member;
import io.github.teamwaff1e.waffle.global.dto.converter.DtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;

    @Transactional
    public void signUp(SignupRequestDto signupRequestDto) {
        validateNickname(signupRequestDto.getNickname());
        validateEmail(signupRequestDto.getEmail());

        Member newMember = Member.builder()
                .name(signupRequestDto.getName())
                .email(signupRequestDto.getEmail())
                .pwd(signupRequestDto.getPwd())
                .nickname(signupRequestDto.getNickname())
                .build();

        authRepository.save(newMember);
    }

    @Transactional(readOnly = true)
    public void validateNickname(String nickname) {
        if(authRepository.existsByNickname(nickname)) {
            throw new MemberNicknameAlreadyExistsException(nickname);
        }
    }
    @Transactional(readOnly = true)
    public void validateEmail(String email) {
        if(authRepository.existsByEmail(email)) {
            throw new MemberEmailAlreadyExistsException(email);
        }
    }
}
