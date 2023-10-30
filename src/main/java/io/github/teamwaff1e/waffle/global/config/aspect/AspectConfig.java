package io.github.teamwaff1e.waffle.global.config.aspect;

import io.github.teamwaff1e.waffle.global.aspect.TraceAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AspectConfig {

    @Bean
    public TraceAspect traceAspect() {
        return new TraceAspect();
    }
}
