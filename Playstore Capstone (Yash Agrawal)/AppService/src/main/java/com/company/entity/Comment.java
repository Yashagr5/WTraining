package com.company.entity;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "comments")
public class Comment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Application ID is required")
    @Column(name = "application_id", nullable = false)
    private Long applicationId; // FK -> Application
    
    @NotNull(message = "User ID is required")
    @Column(name = "user_id", nullable = false)
    private Long userId; // FK -> User
    
    @NotBlank(message = "Comment description is required")
    @Column(nullable = false, length = 1000)
    private String description;
    
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must not exceed 5")
    @Column(name = "rating")
    private Integer rating; // optional rating
    
    @Column(name = "created_date", nullable = false)
    private LocalDate createdDate;
    
    @Builder.Default
    @Column(name = "is_flagged")
    private Boolean isFlagged = false;
    
    @Builder.Default
    @Column(name = "helpful_count")
    private Long helpfulCount = 0L;
    
    @PrePersist
    protected void onCreate() {
        if (createdDate == null) {
            createdDate = LocalDate.now();
        }
        if (isFlagged == null) {
            isFlagged = false;
        }
        if (helpfulCount == null) {
            helpfulCount = 0L;
        }
    }
} 