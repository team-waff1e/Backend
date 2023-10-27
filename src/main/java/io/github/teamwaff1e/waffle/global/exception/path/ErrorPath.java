package io.github.teamwaff1e.waffle.global.exception.path;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorPath {

    private static final String ERRORS = "/errors";

    public static final String UNAUTHENTICATED = ERRORS + "/unauthenticated";

}
