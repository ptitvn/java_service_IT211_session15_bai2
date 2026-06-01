package com.example.bai2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ApiSecurityConfig {

    @Bean
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/api/**")
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/register", "/api/auth/login").permitAll()
                        .requestMatchers("/api/**").authenticated()
                )
                // Vô hiệu hóa form login mặc định
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable())
                // Cấu hình CSRF
                .csrf(csrf -> csrf
                                // Tuỳ chọn 1: vô hiệu hóa CSRF cho API stateless
                                .disable()

                        // Tuỳ chọn 2: nếu muốn dùng CSRF token qua header/cookie
                        // .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                );

        return http.build();
    }
}

