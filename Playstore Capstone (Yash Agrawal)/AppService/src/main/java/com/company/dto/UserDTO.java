package com.company.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Integer userId;
    private String userName;
    private String email;
    private String phone;
    private LocalDateTime createdAt;
    private Boolean isActive;
    private String fullName;
    private String address;
    
    // Convenience method for compatibility
    public String getUsername() {
        return userName;
    }
} 