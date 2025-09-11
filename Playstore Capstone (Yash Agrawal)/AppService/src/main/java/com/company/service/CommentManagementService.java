package com.company.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.dto.CommentDTO;
import com.company.entity.Comment;
import com.company.repo.CommentRepository;
import com.company.repo.AppRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentManagementService {

    private final CommentRepository commentRepository;
    private final AppRepository appRepository;

    @Transactional
    public CommentDTO addComment(CommentDTO dto) {
        Comment comment = Comment.builder()
                .applicationId(dto.getApplicationId())
                .userId(dto.getUserId())
                .description(dto.getDescription())
                .rating(dto.getRating())
                .build();

        Comment saved = commentRepository.save(comment);
        
        // Update app's average rating if rating was provided
        if (dto.getRating() != null) {
            updateAppRating(dto.getApplicationId());
        }
        
        return toDTO(saved);
    }

    @Transactional(readOnly = true)
    public List<CommentDTO> getCommentsByApp(Long appId) {
        return commentRepository.findByApplicationId(appId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CommentDTO> getCommentsByUser(Long userId) {
        return commentRepository.findByUserId(userId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<CommentDTO> getCommentsByOwnerApps(Long ownerId) {
        // Get all apps by owner and then get all comments
        return appRepository.findByOwnerId(ownerId).stream()
                .flatMap(app -> commentRepository.findByApplicationId(app.getId()).stream())
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void markAsHelpful(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        comment.setHelpfulCount(comment.getHelpfulCount() + 1);
        commentRepository.save(comment);
    }
    
    private void updateAppRating(Long appId) {
        List<Comment> comments = commentRepository.findByApplicationId(appId);
        double avgRating = comments.stream()
                .filter(c -> c.getRating() != null)
                .mapToInt(Comment::getRating)
                .average()
                .orElse(0.0);
        
        appRepository.findById(appId).ifPresent(app -> {
            app.setRating(BigDecimal.valueOf(avgRating));
            appRepository.save(app);
        });
    }

    private CommentDTO toDTO(Comment comment) {
        return CommentDTO.builder()
                .id(comment.getId())
                .applicationId(comment.getApplicationId())
                .userId(comment.getUserId())
                .description(comment.getDescription())
                .rating(comment.getRating())
                .createdDate(comment.getCreatedDate())
                .isFlagged(comment.getIsFlagged())
                .helpfulCount(comment.getHelpfulCount())
                .build();
    }
} 