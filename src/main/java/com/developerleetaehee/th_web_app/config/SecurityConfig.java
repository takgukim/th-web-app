package com.developerleetaehee.th_web_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                        // 이거는 제외함
                        //.ignoringRequestMatchers("/api/boards/**")
                        // 아래는 csrf 토큰읽어서 검증함
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                )
                .formLogin(form -> form.disable())
                .httpBasic(httpBasic -> httpBasic.disable())
                // IF_REQUIRED, STATELESS
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.))
                .authorizeHttpRequests(auth -> auth
                        // authenticated() : 로그인 기반 , permitAll() : 모두
                        .requestMatchers("/api/**").permitAll()
                        .anyRequest().permitAll()
                );

        return http.build();
    }
}
