package com.company.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.dto.OwnerNotifyDto;
import com.company.service.NotifiedOwnerService;

@Controller
@RequestMapping("/api") // base path for REST APIs
public class NotificationController {
	private final NotifiedOwnerService service;

    public NotificationController(NotifiedOwnerService service) {
        this.service = service;
    }

    // REST endpoint that receives notifications from owner-service
    @PostMapping(path = "/owner-notify", consumes = "application/json")
    public ResponseEntity<String> acceptOwnerNotify(@RequestBody OwnerNotifyDto dto) {
        try {
            service.saveNotification(dto.getOwnerId(), dto.getOwnerName());
            return ResponseEntity.ok("✅ Notification saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("❌ Failed to save notification: " + e.getMessage());
        }
    }
}
