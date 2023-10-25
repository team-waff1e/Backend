package io.github.teamwaff1e.waffle.domain.auth.controller;

import io.github.teamwaff1e.waffle.domain.auth.dto.SignupRequestDto;
import io.github.teamwaff1e.waffle.domain.auth.dto.ValidateEmailRequestDto;
import io.github.teamwaff1e.waffle.domain.auth.dto.ValidateNicknameRequestDto;
import io.github.teamwaff1e.waffle.domain.auth.exception.MemberEmailAlreadyExistsException;
import io.github.teamwaff1e.waffle.domain.auth.exception.MemberNicknameAlreadyExistsException;
import io.github.teamwaff1e.waffle.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody SignupRequestDto signupRequestDto) {
        authService.signUp(signupRequestDto);
    }

    @PostMapping("/signup/nickname")
    @ResponseStatus(HttpStatus.OK)
    public void validateNickname(@RequestBody ValidateNicknameRequestDto validateNicknameRequestDto) {
        authService.validateNickname(validateNicknameRequestDto.getNickname());
    }

    @PostMapping("/signup/email")
    @ResponseStatus(HttpStatus.OK)
    public void validateEmail(@RequestBody ValidateEmailRequestDto validateEmailRequestDto) {
        authService.validateEmail(validateEmailRequestDto.getEmail());
    }
}
