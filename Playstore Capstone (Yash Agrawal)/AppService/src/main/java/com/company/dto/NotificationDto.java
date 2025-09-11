package com.company.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationDto {

    private Long id;

    // Links to application / owner / user
    private Long applicationId;   // FK to Application
    private Long ownerId;         // FK to Owner
    private Long userId;          // FK to User

    // Core Notification fields
    private String title;
    private String description;
    private String notificationType;  // GENERAL, UPDATE, DOWNLOAD, REVIEW, USER, OWNER, APP
    private String priority;          // LOW, MEDIUM, HIGH, URGENT

    private Boolean isSent;
    private LocalDate notificationDate;
    private LocalDate sentDate;

    // Recipient
    private String recipientEmail;

    // Retry handling
    private Integer retryCount;
} 