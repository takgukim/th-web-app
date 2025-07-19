package com.developerleetaehee.th_web_app;

import com.developerleetaehee.th_web_app.config.CorsProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig {
    private final CorsProperties corsProperties;
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**") // 허용할 경로
                        .allowedOrigins(corsProperties.getAllowedOrigins().toArray(new String[0])) // 여러 origin 허용하며 콤마로 구분할 것
                        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE");
            }
        };
    }
}