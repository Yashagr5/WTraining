package com.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerResponseDto {
    
    private Integer ownerId;
    private String ownerName;
    private String email;
    private String phone;
    private String companyName;
    private String businessAddress;
    private String businessType;
    private String role;
    private String approvalStatus;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
