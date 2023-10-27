package io.github.teamwaff1e.waffle.domain.auth.controller;

import io.github.teamwaff1e.waffle.domain.auth.constant.AuthConstant;
import io.github.teamwaff1e.waffle.domain.auth.dto.request.LoginRequestDto;
import io.github.teamwaff1e.waffle.global.exception.auth.IllegalLoginStateException;
import io.github.teamwaff1e.waffle.domain.auth.service.AuthService;
import io.github.teamwaff1e.waffle.domain.auth.vo.AuthVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public void login(HttpServletRequest request,
                                        @Validated @RequestBody LoginRequestDto loginRequestDto) {
        // todo: decrypt password

        AuthVo authVo = authService.login(loginRequestDto);

        String authSessionKey = AuthConstant.AUTH_SESSION_KEY;
        HttpSession session = request.getSession();
        session.setAttribute(authSessionKey, authVo);
        session.setMaxInactiveInterval(AuthConstant.AUTH_SESSION_MAX_INTERVAL);
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(HttpServletRequest request) { // todo?: inactivate JSESSIONID cookie
        HttpSession session = request.getSession(false);
        if (session == null) {
            throw new IllegalLoginStateException();
        }
        session.invalidate();
    }
}
