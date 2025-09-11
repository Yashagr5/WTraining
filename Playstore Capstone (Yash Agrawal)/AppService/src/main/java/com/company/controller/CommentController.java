package com.company.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.company.dto.CommentDTO;
import com.company.service.CommentManagementService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
@Tag(name = "Comment API", description = "Endpoints for managing app comments and reviews")
public class CommentController {

    private final CommentManagementService commentService;

    @Operation(summary = "Add a new comment", description = "Creates a new comment/review for an app")
    @PostMapping
    public ResponseEntity<CommentDTO> addComment(@RequestBody CommentDTO commentDTO) {
        return ResponseEntity.ok(commentService.addComment(commentDTO));
    }

    @Operation(summary = "Get comments by app", description = "Returns all comments for a specific app")
    @GetMapping("/app/{appId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByApp(@PathVariable Long appId) {
        return ResponseEntity.ok(commentService.getCommentsByApp(appId));
    }

    @Operation(summary = "Get comments by user", description = "Returns all comments made by a specific user")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(commentService.getCommentsByUser(userId));
    }
    
    @Operation(summary = "Get comments for owner's apps", description = "Returns all comments on apps owned by a specific owner")
    @GetMapping("/owner/{ownerId}/apps")
    public ResponseEntity<List<CommentDTO>> getCommentsByOwnerApps(@PathVariable Long ownerId) {
        return ResponseEntity.ok(commentService.getCommentsByOwnerApps(ownerId));
    }

    @Operation(summary = "Mark comment as helpful", description = "Increments the helpful count for a comment")
    @PutMapping("/{commentId}/helpful")
    public ResponseEntity<Void> markAsHelpful(@PathVariable Long commentId) {
        commentService.markAsHelpful(commentId);
        return ResponseEntity.ok().build();
    }
} 