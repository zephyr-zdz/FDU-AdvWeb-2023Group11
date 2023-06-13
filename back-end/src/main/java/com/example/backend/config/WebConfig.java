package com.example.backend.config;

import com.example.backend.inteceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/api/user/**")
                .excludePathPatterns("/api/user/login","/api/user/register");
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .exposedHeaders("Authorization")
                .allowCredentials(true)
                .allowedMethods("*")
                .allowedHeaders("*").maxAge
                        (3600);
        WebMvcConfigurer.super.addCorsMappings(registry);
    }
}
