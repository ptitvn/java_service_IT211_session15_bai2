package com.example.bai2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ApiSecurityConfig {

    @Bean
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/api/**")

                // Cấu hình phân quyền
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/register", "/api/auth/login").permitAll()
                        .requestMatchers("/api/**").authenticated()
                )

                // Vô hiệu hóa form login mặc định của Spring Security
                .formLogin(form -> form.disable())

                // Vô hiệu hóa HTTP Basic Authentication
                .httpBasic(basic -> basic.disable())

                // Cấu hình session: STATELESS vì REST API dùng token, không dùng session
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // Vô hiệu hóa CSRF vì client di động dùng token trong header,
                // không dùng cookie/session truyền thống nên không cần bảo vệ CSRF
                .csrf(csrf -> csrf.disable());

        // Tuỳ chọn thay thế: nếu muốn dùng CSRF token qua header/cookie
        // .csrf(csrf -> csrf
        //         .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        // );

        return http.build();
    }
}