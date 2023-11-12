package io.github.teamwaff1e.waffle.global.interceptor;

import io.github.teamwaff1e.waffle.global.annotation.Login;
import io.github.teamwaff1e.waffle.global.constant.auth.AuthConstant;
import io.github.teamwaff1e.waffle.global.error.path.ErrorPath;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Arrays;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        Login login = Arrays.stream(handlerMethod.getMethodParameters())
                .filter(methodParameter -> methodParameter.hasParameterAnnotation(Login.class))
                .findAny()
                .orElseThrow(IllegalStateException::new) // todo
                .getParameterAnnotation(Login.class);

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(AuthConstant.AUTH_SESSION_KEY) == null
                || login.allowUnauthenticated()) {
            request.getRequestDispatcher(ErrorPath.UNAUTHENTICATED).forward(request, response);
            return false;
        }

        return true;
    }
}
