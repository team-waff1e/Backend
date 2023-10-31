package io.github.teamwaff1e.waffle.global.config.member;


import io.github.teamwaff1e.waffle.domain.member.dto.converter.MemberToResponseConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemberConfig {

    @Bean
    public MemberToResponseConverter MemberToResponseConverter() {
        return new MemberToResponseConverter();
    }
}
