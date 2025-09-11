package com.company.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
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
@Table(name = "downloads")
public class Download {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Application ID is required")
    @Column(name = "application_id", nullable = false)
    private Long applicationId; // FK -> Application
    
    @NotNull(message = "User ID is required")
    @Column(name = "user_id", nullable = false)
    private Long userId; // FK -> User
    
    @Column(name = "download_date", nullable = false)
    private LocalDate downloadDate;
    
    @Builder.Default
    @Column(name = "download_status", length = 20)
    private String downloadStatus = "COMPLETED"; // INITIATED, IN_PROGRESS, COMPLETED, FAILED
    
    @Column(name = "device_info", length = 200)
    private String deviceInfo;
    
    @Column(name = "ip_address", length = 45)
    private String ipAddress;
    
    @Column(name = "download_size_mb")
    private Double downloadSizeMb;
    
    @Column(name = "download_duration_seconds")
    private Long downloadDurationSeconds;
    
    @Builder.Default
    @Column(name = "is_successful")
    private Boolean isSuccessful = true;
    
    @Column(name = "error_message", length = 500)
    private String errorMessage;
    
    @PrePersist
    protected void onCreate() {
        if (downloadDate == null) {
            downloadDate = LocalDate.now();
        }
        if (downloadStatus == null) {
            downloadStatus = "COMPLETED";
        }
        if (isSuccessful == null) {
            isSuccessful = true;
        }
    }
} 