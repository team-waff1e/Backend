package io.github.teamwaff1e.waffle.domain.auth.vo;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class AuthVo {

    private final Long memberId;
    private final boolean authenticated;

    public static AuthVo from(long memberId) {
        return new AuthVo(memberId, true);
    }

    public static AuthVo newUnauthenticatedInstance() {
        return new AuthVo(null, false);
    }
}
