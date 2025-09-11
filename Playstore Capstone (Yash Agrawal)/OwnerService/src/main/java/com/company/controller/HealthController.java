package com.company.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthController {
    
    // Changed from "/" to "/health" to avoid conflict with OwnerController
    @GetMapping("/health")
    public Map<String, Object> health() {
        Map<String, Object> response = new HashMap<>();
        response.put("service", "OwnerService");
        response.put("status", "running");
        response.put("message", "Owner Service is up and running");
        response.put("endpoints", Map.of(
            "register", "POST /api/owners/register",
            "login", "POST /api/owners/login",
            "health", "GET /api/owners/health"
        ));
        return response;
    }
    
    @GetMapping("/api")
    public Map<String, Object> api() {
        return health();
    }
} 