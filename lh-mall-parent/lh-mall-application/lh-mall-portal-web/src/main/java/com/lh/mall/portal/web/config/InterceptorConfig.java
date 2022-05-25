package com.lh.mall.portal.web.config;

import com.lh.mall.portal.web.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 将拦截器加到容器内，注册，这样才会生效，否则不会生效的。
        registry.addInterceptor(authInterceptor())
                // 所有的都拦截 /** 表示。
                .addPathPatterns("/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    @Bean
    public AuthInterceptor authInterceptor() {
        return new AuthInterceptor();
    }
}
