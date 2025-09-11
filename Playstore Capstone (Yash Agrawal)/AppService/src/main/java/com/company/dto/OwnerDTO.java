package com.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerDTO {
    
    private Integer ownerId;
    private String ownerName;
    private String email;
    private String phone;
    private LocalDateTime createdAt;
    private Boolean isActive;
    private String role;
    private String approvalStatus;
    private String companyName;
    private String businessAddress;
    private String businessType;
}
