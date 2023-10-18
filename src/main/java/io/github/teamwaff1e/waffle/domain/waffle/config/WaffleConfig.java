package io.github.teamwaff1e.waffle.domain.waffle.config;

import io.github.teamwaff1e.waffle.domain.waffle.dto.converter.WaffleToResponseConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WaffleConfig {

    @Bean
    public WaffleToResponseConverter waffleToResponseConverter() {
        return new WaffleToResponseConverter();
    }
}
