package io.github.teamwaff1e.waffle.global.config;

import io.github.teamwaff1e.waffle.global.argumentresolver.LoginAuthVoArgumentResolver;
import io.github.teamwaff1e.waffle.global.interceptor.LoginInterceptor;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginAuthVoArgumentResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginInterceptor())
//                .order(1)
//                .addPathPatterns("/**") // todo
//                .excludePathPatterns(
//                        "/auth/**",
//                        "/errors/**",
//                        "/members",
//                        "/members/{[0-9]+}"
//                );
    }
}
