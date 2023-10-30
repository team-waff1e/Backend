package io.github.teamwaff1e.waffle.global.error.controller;

import io.github.teamwaff1e.waffle.global.exception.auth.WAuthenticationException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/errors")
public class ErrorController {

    @RequestMapping("/unauthenticated")
    public void unauthenticatedRequest(HttpServletRequest request, HttpServletResponse response) {
        throw new WAuthenticationException();
    }
}
