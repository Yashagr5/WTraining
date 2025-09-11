package com.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequestDTO {
    
    private Integer appId;
    private String appName;
    private Integer ownerId;
    private String ownerName;
    private String notificationType;
    private String message;
    private String title;
}
