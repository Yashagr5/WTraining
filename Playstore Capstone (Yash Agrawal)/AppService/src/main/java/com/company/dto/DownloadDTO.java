package com.company.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DownloadDTO {
    private Long id;
    private Long applicationId;
    private Long userId;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate downloadDate;
    
    private String downloadStatus;
    private String deviceInfo;
    private String ipAddress;
    private Double downloadSizeMb;
    private Long downloadDurationSeconds;
    private Boolean isSuccessful;
    private String errorMessage;
} 