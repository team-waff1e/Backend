package io.github.teamwaff1e.waffle.global.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@Slf4j
public class TraceAspect {

    @Around("@annotation(io.github.teamwaff1e.waffle.global.annotation.Trace)")
    public Object trace(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();

        log.info("[trace] [ call ] {} args={}", joinPoint.getSignature(), args);
        Object result = joinPoint.proceed(args);
        log.info("[trace] [return] {} args={}", joinPoint.getSignature(), args);

        return result;
    }
}
