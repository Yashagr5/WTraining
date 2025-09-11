package com.company.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "notifications")
public class Notification {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Recipient ID is required")
    @Column(name = "recipient_id", nullable = false)
    private Long recipientId; // Can be User ID, Owner ID, or App ID depending on type
    
    @NotBlank(message = "Title is required")
    @Column(nullable = false, length = 200)
    private String title;
    
    @NotBlank(message = "Description is required")
    @Column(nullable = false, length = 1000)
    private String description;
    
    @Column(name = "notification_date", nullable = false)
    private LocalDate notificationDate;
    
    @Builder.Default
    @Column(name = "notification_type", length = 50)
    private String notificationType = "GENERAL"; // GENERAL, UPDATE, DOWNLOAD, REVIEW, USER, OWNER, APP
    
    @Builder.Default
    @Column(name = "is_sent")
    private Boolean isSent = false;
    
    @Builder.Default
    @Column(name = "priority", length = 20)
    private String priority = "MEDIUM"; // LOW, MEDIUM, HIGH, URGENT
    
    @Column(name = "recipient_email", length = 255)
    private String recipientEmail;
    
    @Column(name = "sent_date")
    private LocalDate sentDate;
    
    @Column(name = "retry_count")
    @Builder.Default
    private Integer retryCount = 0;
    
    // Additional fields for tracking
    @Column(name = "application_id")
    private Long applicationId; // Optional: related app if applicable
    
    @PrePersist
    protected void onCreate() {
        if (notificationDate == null) {
            notificationDate = LocalDate.now();
        }
        if (isSent == null) {
            isSent = false;
        }
        if (notificationType == null) {
            notificationType = "GENERAL";
        }
        if (priority == null) {
            priority = "MEDIUM";
        }
        if (retryCount == null) {
            retryCount = 0;
        }
    }
} 