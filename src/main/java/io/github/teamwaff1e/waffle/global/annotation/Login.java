package io.github.teamwaff1e.waffle.global.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Login AuthVo authVo: authVo.isAuthenticated() == true
 * @Login(allowUnauthenticated = true) AuthVo authVo: authVo.isAuthenticated() can be either true or false
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Login {
    boolean allowUnauthenticated() default false;
}