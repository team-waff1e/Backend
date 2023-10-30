package io.github.teamwaff1e.waffle.global.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE}) // todo: 범위 정하기
@Retention(RetentionPolicy.RUNTIME)
public @interface Trace {
}
