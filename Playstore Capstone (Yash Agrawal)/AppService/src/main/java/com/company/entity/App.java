package com.company.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
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
@Table(name = "applications")
public class App {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="app_id")
    private Long id;
    
    @NotBlank(message = "Application name is required")
    @Column(name = "app_name", nullable = false, length = 100)
    private String name;
    
    @Column(length = 1000)
    private String description;
    
    @NotBlank(message = "Version is required")
    @Column(nullable = false, length = 20)
    private String version;
    
    @NotNull(message = "Upload date is required")
    @Column(name = "upload_date", nullable = false)
    private LocalDate uploadDate;
    
    @Column(name = "release_date")
    private LocalDate releaseDate;
    
    @NotBlank(message = "Status is required")
    @Column(nullable = false, length = 20)
    private String status; // Active, Hidden, Inactive
    
    @NotNull(message = "Category ID is required")
    @Column(name = "category_id", nullable = false)
    private Long categoryId; // FK -> Category
    
    @NotNull(message = "Owner ID is required")
    @Column(name = "owner_id", nullable = false)
    private Long ownerId; // FK -> Owner
    
    @DecimalMin(value = "0.0", message = "Rating must be at least 0.0")
    @DecimalMax(value = "5.0", message = "Rating must not exceed 5.0")
    @Column(precision = 2, scale = 1)
    private BigDecimal rating; // avg rating
    
    @Column(name = "icon_url", length = 500)
    private String iconUrl;
    
    @Column(name = "apk_url", length = 500)
    private String apkUrl;
    
    @Builder.Default
    @Column(name = "download_count")
    private Long downloadCount = 0L;
    
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        if (uploadDate == null) {
            uploadDate = LocalDate.now();
        }
        if (status == null) {
            status = "Active";
        }
        if (downloadCount == null) {
            downloadCount = 0L;
        }
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
}
