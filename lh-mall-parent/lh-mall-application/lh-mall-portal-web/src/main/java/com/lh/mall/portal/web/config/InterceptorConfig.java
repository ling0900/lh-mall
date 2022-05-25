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
                .addPathPatterns("/**")
                .excludePathPatterns("/user-member/login")
//                这里如果不排除，则这个接口，会进行拦截器。
//                下面的这个，如果程序运行过程中，出现了异常界面，则拦截器依旧会去拦截。favicon.ico/**在生成验证码的时候，
//                会返回这个页面，不排除就出问题。 默认情况下，浏览器会自动请求favicon.ico文件。 favicon.ico文件是显示在浏览器网址栏中的小图标
                .excludePathPatterns("/code/**").excludePathPatterns("/favicon.ico/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    @Bean
    public AuthInterceptor authInterceptor() {
        return new AuthInterceptor();
    }
}
