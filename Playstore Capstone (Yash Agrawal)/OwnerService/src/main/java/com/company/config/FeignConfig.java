package com.company.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            // Add any headers needed for inter-service communication
            requestTemplate.header("Content-Type", "application/json");
            requestTemplate.header("Accept", "application/json");
            // Note: We're not adding JWT token here as inter-service endpoints should be public
        };
    }
} 