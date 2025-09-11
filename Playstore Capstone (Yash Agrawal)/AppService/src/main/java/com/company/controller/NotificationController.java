package com.company.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.company.dto.NotificationDto;
import com.company.entity.Notification;
import com.company.service.NotificationManagementService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
@Tag(name = "Notification API", description = "Endpoints for sending notifications to Owners and Users")
public class NotificationController {

    private final NotificationManagementService notificationService;

    @Operation(summary = "Send notification to Owner", description = "Sends a notification to an Owner by their ID")
    @PostMapping("/owners/{ownerId}")
    public ResponseEntity<Notification> sendToOwner(
            @Parameter(description = "Owner ID", example = "1") @PathVariable Long ownerId,
            @Parameter(description = "Title of the notification", example = "New Update Available") @RequestParam String title,
            @Parameter(description = "Description of the notification", example = "Your app got a new version update.") @RequestParam String description) {
        
        Notification notification = notificationService.sendNotificationToOwner(ownerId, title, description);
        return ResponseEntity.ok(notification);
    }

    @Operation(summary = "Send notification to User", description = "Sends a notification to a User by their ID")
    @PostMapping("/users/{userId}")
    public ResponseEntity<Notification> sendToUser(
            @Parameter(description = "User ID", example = "5") @PathVariable Long userId,
            @Parameter(description = "Title of the notification", example = "Special Offer") @RequestParam String title,
            @Parameter(description = "Description of the notification", example = "You got 50% off on your next purchase!") @RequestParam String description) {
        
        Notification notification = notificationService.sendNotificationToUser(userId, title, description);
        return ResponseEntity.ok(notification);
    }
    
    @Operation(summary = "Announce app update", description = "Notifies all users who have downloaded an app about an update")
    @PostMapping("/app/{appId}/update")
    public ResponseEntity<String> announceAppUpdate(
            @Parameter(description = "App ID", example = "1") @PathVariable Long appId,
            @Parameter(description = "App name", example = "My App") @RequestParam String appName,
            @Parameter(description = "Update description", example = "New features and bug fixes") @RequestParam String updateDescription) {
        
        notificationService.announceAppUpdate(appId, appName, updateDescription);
        return ResponseEntity.ok("Update notifications sent to all users who have downloaded the app");
    }
    
    @Operation(summary = "Get notifications by recipient", description = "Returns all notifications for a specific recipient")
    @GetMapping("/recipient/{recipientId}")
    public ResponseEntity<List<NotificationDto>> getNotificationsByRecipient(@PathVariable Long recipientId) {
        return ResponseEntity.ok(notificationService.getNotificationsByRecipient(recipientId));
    }
    
    @Operation(summary = "Get notifications by type", description = "Returns all notifications of a specific type")
    @GetMapping("/type/{type}")
    public ResponseEntity<List<NotificationDto>> getNotificationsByType(@PathVariable String type) {
        return ResponseEntity.ok(notificationService.getNotificationsByType(type));
    }
} 