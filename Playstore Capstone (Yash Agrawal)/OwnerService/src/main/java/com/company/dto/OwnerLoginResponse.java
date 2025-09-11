package com.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerLoginResponse {
    
    private String token;
    private String ownerName;
    private String email;
    private String companyName;
    private String role;
    private String approvalStatus;
    
    public OwnerLoginResponse(String token, String ownerName, String email, String companyName, String role) {
        this.token = token;
        this.ownerName = ownerName;
        this.email = email;
        this.companyName = companyName;
        this.role = role;
    }
}
