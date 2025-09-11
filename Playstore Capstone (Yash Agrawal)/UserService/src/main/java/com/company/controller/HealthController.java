package com.company.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthController {
    
    // Changed from "/" to "/health" to avoid conflicts
    @GetMapping("/health")
    public Map<String, Object> health() {
        Map<String, Object> response = new HashMap<>();
        response.put("service", "UserService");
        response.put("status", "running");
        response.put("message", "User Service is up and running");
        response.put("endpoints", Map.of(
            "register", "POST /api/users/register",
            "login", "POST /api/users/login",
            "health", "GET /api/users/health"
        ));
        return response;
    }
    
    @GetMapping("/api")
    public Map<String, Object> api() {
        return health();
    }
} 