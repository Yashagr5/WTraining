package com.company.controller;

import com.company.dto.AppVisibilityRequest;
import com.company.dto.AppFeatureRequest;
import com.company.service.OwnerAppManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/owners/apps")
@RequiredArgsConstructor

public class OwnerAppManagementController {
    
    private final OwnerAppManagementService appManagementService;
    
    @PutMapping("/{appId}/visibility")
    public ResponseEntity<?> updateAppVisibility(
            @PathVariable Integer appId,
            @RequestBody AppVisibilityRequest request,
            Authentication authentication) {
        try {
            String ownerName = authentication.getName();
            appManagementService.updateAppVisibility(appId, request.isVisible(), ownerName);
            return ResponseEntity.ok("App visibility updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    
    @PutMapping("/{appId}/featured")
    public ResponseEntity<?> toggleFeaturedStatus(
            @PathVariable Integer appId,
            @RequestBody AppFeatureRequest request,
            Authentication authentication) {
        try {
            String ownerName = authentication.getName();
            appManagementService.toggleFeaturedStatus(appId, request.isFeatured(), ownerName);
            return ResponseEntity.ok("Featured status updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    
    @PutMapping("/{appId}/maintenance")
    public ResponseEntity<?> toggleMaintenanceMode(
            @PathVariable Integer appId,
            @RequestParam Boolean maintenanceMode,
            Authentication authentication) {
        try {
            String ownerName = authentication.getName();
            appManagementService.toggleMaintenanceMode(appId, maintenanceMode, ownerName);
            return ResponseEntity.ok("Maintenance mode updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    
    @PutMapping("/{appId}/beta")
    public ResponseEntity<?> toggleBetaStatus(
            @PathVariable Integer appId,
            @RequestParam Boolean betaStatus,
            Authentication authentication) {
        try {
            String ownerName = authentication.getName();
            appManagementService.toggleBetaStatus(appId, betaStatus, ownerName);
            return ResponseEntity.ok("Beta status updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    
    @GetMapping("/my-apps")
    public ResponseEntity<?> getMyApps(Authentication authentication) {
        try {
            String ownerName = authentication.getName();
            List<Map<String, Object>> apps = appManagementService.getOwnerApps(ownerName);
            return ResponseEntity.ok(apps);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    
    @GetMapping("/my-apps/statistics")
    public ResponseEntity<?> getMyAppStatistics(Authentication authentication) {
        try {
            String ownerName = authentication.getName();
            Map<String, Object> stats = appManagementService.getOwnerAppStatistics(ownerName);
            return ResponseEntity.ok(stats);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    
    @GetMapping("/my-apps/performance")
    public ResponseEntity<?> getAppPerformanceMetrics(
            @RequestParam(required = false) Integer appId,
            @RequestParam(defaultValue = "30") int days,
            Authentication authentication) {
        try {
            String ownerName = authentication.getName();
            Map<String, Object> metrics = appManagementService.getAppPerformanceMetrics(ownerName, appId, days);
            return ResponseEntity.ok(metrics);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    
    @PostMapping("/{appId}/announcement")
    public ResponseEntity<?> createAppAnnouncement(
            @PathVariable Integer appId,
            @RequestBody Map<String, String> announcement,
            Authentication authentication) {
        try {
            String ownerName = authentication.getName();
            String title = announcement.get("title");
            String message = announcement.get("message");
            appManagementService.createAppAnnouncement(appId, title, message, ownerName);
            return ResponseEntity.ok("Announcement created successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    
    @GetMapping("/{appId}/reviews/summary")
    public ResponseEntity<?> getAppReviewsSummary(
            @PathVariable Integer appId,
            Authentication authentication) {
        try {
            String ownerName = authentication.getName();
            Map<String, Object> summary = appManagementService.getAppReviewsSummary(appId, ownerName);
            return ResponseEntity.ok(summary);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
