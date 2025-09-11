package com.company.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authz -> authz
                // Public endpoints (temporarily allowing downloads and owner listings for testing)
                .requestMatchers(
                    "/api/categories/**",
                    "/api/apps/search/**",
                    "/api/apps/featured",
                    "/api/apps/trending",
                    "/api/apps/{id}",
                    "/api/apps/owner/**",
                    "/api/apps/*/increment-download",
                    "/api/downloads/**",
                    "/api/comments/app/**",
                    "/h2-console/**",
                    "/actuator/**"
                ).permitAll()
                // Protected endpoints
                .requestMatchers(
                    "/api/apps/create",
                    "/api/apps/update/**",
                    "/api/apps/delete/**",
                    "/api/notifications/**"
                ).authenticated()
                .anyRequest().permitAll()
            )
            .headers(headers -> headers.frameOptions(frame -> frame.disable())); // For H2 console
            
        return http.build();
    }
} 