package com.company.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppDTO {
    private Long id;
    private String name;
    private String description;
    private String version;
    private LocalDate uploadDate;
    private LocalDate releaseDate;
    private String status;
    private Long categoryId;
    private Long ownerId;
    private BigDecimal rating;
    private String iconUrl;
    private String apkUrl;
    private Long downloadCount;
} 