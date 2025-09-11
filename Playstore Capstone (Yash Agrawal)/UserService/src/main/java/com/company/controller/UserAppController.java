package com.company.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.client.AppClient;
import com.company.client.CommentClient;
import com.company.dto.AppDTO;
import com.company.dto.CommentDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users/apps")
@RequiredArgsConstructor

public class UserAppController {
    
    private final AppClient appClient;
    private final CommentClient commentClient;
    
    @GetMapping("/search")
    public ResponseEntity<?> searchApps(@RequestParam String searchTerm) {
        try {
            List<AppDTO> apps = appClient.searchApps(searchTerm);
            return ResponseEntity.ok(apps);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error searching apps: " + e.getMessage());
        }
    }
    
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> getAppsByCategory(@PathVariable Integer categoryId) {
        try {
            List<AppDTO> apps = appClient.getAppsByCategory(categoryId);
            return ResponseEntity.ok(apps);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error getting apps by category: " + e.getMessage());
        }
    }
    
    @GetMapping("/featured")
    public ResponseEntity<?> getFeaturedApps() {
        try {
            List<AppDTO> apps = appClient.getFeaturedApps();
            return ResponseEntity.ok(apps);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error getting featured apps: " + e.getMessage());
        }
    }
    
    @GetMapping("/{appId}")
    public ResponseEntity<?> getAppDetails(@PathVariable Integer appId) {
        try {
            AppDTO app = appClient.getAppById(appId);
            return ResponseEntity.ok(app);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error getting app details: " + e.getMessage());
        }
    }
    
    @PostMapping("/{appId}/download")
    public ResponseEntity<?> downloadApp(@PathVariable Integer appId, Authentication authentication) {
        try {
            String username = authentication.getName();
            String result = appClient.incrementDownloadCount(appId);
            return ResponseEntity.ok(Map.of("message", "App download initiated", "result", result));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error downloading app: " + e.getMessage());
        }
    }
    
    @GetMapping("/{appId}/comments")
    public ResponseEntity<?> getAppComments(@PathVariable Integer appId) {
        try {
            List<CommentDTO> comments = commentClient.getCommentsByApp(appId);
            return ResponseEntity.ok(comments);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error getting app comments: " + e.getMessage());
        }
    }
    
    @PostMapping("/{appId}/comments")
    public ResponseEntity<?> addComment(@PathVariable Long appId, 
                                       @RequestBody CommentDTO comment,
                                       Authentication authentication) {
        try {
            String username = authentication.getName();
            comment.setApplicationId(appId);
            // In real implementation, we'd get userId from UserService
            comment.setUserId(1L); // Mock user ID
            
            CommentDTO savedComment = commentClient.createComment(comment);
            return ResponseEntity.ok(savedComment);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error adding comment: " + e.getMessage());
        }
    }
    
    @GetMapping("/my-comments")
    public ResponseEntity<?> getMyComments(Authentication authentication) {
        try {
            String username = authentication.getName();
            // In real implementation, we'd get userId from UserService
            Integer userId = 1; // Mock user ID
            
            List<CommentDTO> comments = commentClient.getCommentsByUser(userId);
            return ResponseEntity.ok(comments);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error getting user comments: " + e.getMessage());
        }
    }
}
