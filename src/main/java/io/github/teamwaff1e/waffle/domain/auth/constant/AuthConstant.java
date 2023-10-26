package io.github.teamwaff1e.waffle.domain.auth.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AuthConstant {

    public static final String AUTH_SESSION_KEY = "AuthVo";
    public static final int AUTH_SESSION_MAX_INTERVAL = 1800;
}
