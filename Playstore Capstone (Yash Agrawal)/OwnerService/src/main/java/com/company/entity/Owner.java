package com.company.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "owners")
public class Owner {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ownerId;

    @Column(nullable = false, unique = true, length = 50)
    private String ownerName;

    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false)
    private String password;   // needed for login authentication

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isActive = true;  // default active
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private ApprovalStatus approvalStatus = ApprovalStatus.APPROVED;  // default approved for immediate access
    
    @Column(length = 100)
    private String companyName;
    
    @Column(length = 255)
    private String businessAddress;
    
    @Column(length = 50)
    private String businessType;
    
    // Convenience methods for JWT
    public String getUsername() {
        return ownerName;
    }
    
    public Boolean getIsActive() {
        return isActive;
    }
    
    public enum ApprovalStatus {
        PENDING, APPROVED, REJECTED, SUSPENDED
    }
}
