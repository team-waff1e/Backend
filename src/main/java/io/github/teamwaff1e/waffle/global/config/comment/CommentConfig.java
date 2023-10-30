package io.github.teamwaff1e.waffle.global.config.comment;

import io.github.teamwaff1e.waffle.domain.comment.dto.converter.CommentToResponseConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommentConfig {

    @Bean
    public CommentToResponseConverter commentToResponseConverter() {
        return new CommentToResponseConverter();
    }
}
