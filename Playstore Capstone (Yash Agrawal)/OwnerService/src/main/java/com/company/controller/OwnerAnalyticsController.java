package com.company.controller;

import com.company.dto.ApiResponse;
import com.company.service.OwnerAnalyticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/owners/analytics")
@RequiredArgsConstructor

@Tag(name = "Owner Analytics", description = "Analytics and insights for app owners")
@SecurityRequirement(name = "Bearer Authentication")
public class OwnerAnalyticsController {
    
    private final OwnerAnalyticsService analyticsService;
    
    @GetMapping("/dashboard/{ownerId}")
    @Operation(summary = "Get owner dashboard analytics", description = "Retrieve comprehensive analytics for owner dashboard")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getOwnerDashboard(@PathVariable Integer ownerId) {
        Map<String, Object> dashboard = analyticsService.getOwnerDashboard(ownerId);
        return ResponseEntity.ok(ApiResponse.success("Dashboard data retrieved successfully", dashboard));
    }
    
    @GetMapping("/app-performance/{ownerId}")
    @Operation(summary = "Get app performance metrics", description = "Retrieve performance metrics for all owner apps")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getAppPerformance(
            @PathVariable Integer ownerId,
            @RequestParam(defaultValue = "30") int days) {
        Map<String, Object> performance = analyticsService.getAppPerformanceMetrics(ownerId, days);
        return ResponseEntity.ok(ApiResponse.success("Performance metrics retrieved successfully", performance));
    }
    
    @GetMapping("/download-trends/{ownerId}")
    @Operation(summary = "Get download trends", description = "Retrieve download trends over time")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getDownloadTrends(
            @PathVariable Integer ownerId,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate) {
        Map<String, Object> trends = analyticsService.getDownloadTrends(ownerId, startDate, endDate);
        return ResponseEntity.ok(ApiResponse.success("Download trends retrieved successfully", trends));
    }
    
    @GetMapping("/revenue-analytics/{ownerId}")
    @Operation(summary = "Get revenue analytics", description = "Retrieve revenue and monetization analytics")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getRevenueAnalytics(@PathVariable Integer ownerId) {
        Map<String, Object> revenue = analyticsService.getRevenueAnalytics(ownerId);
        return ResponseEntity.ok(ApiResponse.success("Revenue analytics retrieved successfully", revenue));
    }
    
    @GetMapping("/user-engagement/{ownerId}")
    @Operation(summary = "Get user engagement metrics", description = "Retrieve user engagement and retention metrics")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getUserEngagement(@PathVariable Integer ownerId) {
        Map<String, Object> engagement = analyticsService.getUserEngagementMetrics(ownerId);
        return ResponseEntity.ok(ApiResponse.success("User engagement metrics retrieved successfully", engagement));
    }
    
    @GetMapping("/review-analytics/{ownerId}")
    @Operation(summary = "Get review analytics", description = "Retrieve review and rating analytics")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getReviewAnalytics(@PathVariable Integer ownerId) {
        Map<String, Object> reviews = analyticsService.getReviewAnalytics(ownerId);
        return ResponseEntity.ok(ApiResponse.success("Review analytics retrieved successfully", reviews));
    }
}
