package io.github.teamwaff1e.waffle.global.interceptor;

import io.github.teamwaff1e.waffle.domain.auth.constant.AuthConstant;
import io.github.teamwaff1e.waffle.global.exception.path.ErrorPath;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(AuthConstant.AUTH_SESSION_KEY) == null) {
            request.getRequestDispatcher(ErrorPath.UNAUTHENTICATED).forward(request, response);
            return false;
        }

        return true;
    }
}
