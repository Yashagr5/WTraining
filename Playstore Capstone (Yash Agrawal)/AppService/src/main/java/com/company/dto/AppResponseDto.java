package com.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppResponseDto {
    
    private Long id;
    private String name;
    private String description;
    private String version;
    private LocalDate uploadDate;
    private LocalDate releaseDate;
    private String status;
    private Long categoryId;
    private String categoryName;
    private Long ownerId;
    private String ownerName;
    private BigDecimal rating;
    private String iconUrl;
    private String apkUrl;
    private Long downloadCount;
    private Boolean isFeatured;
    private Boolean isVisible;
    private BigDecimal price;
    private String tags;
}
