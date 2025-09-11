package com.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppCreateRequest {
    
    @NotBlank(message = "App name is required")
    @Size(min = 2, max = 100, message = "App name must be between 2 and 100 characters")
    private String name;
    
    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 1000, message = "Description must be between 10 and 1000 characters")
    private String description;
    
    @NotBlank(message = "Version is required")
    @Size(max = 20, message = "Version cannot exceed 20 characters")
    private String version;
    
    @NotNull(message = "Category ID is required")
    private Integer categoryId;
    
    @DecimalMin(value = "0.0", message = "Price cannot be negative")
    @DecimalMax(value = "999.99", message = "Price cannot exceed 999.99")
    private BigDecimal price = BigDecimal.ZERO;
    
    @Size(max = 500, message = "Tags cannot exceed 500 characters")
    private String tags;
    
    @Size(max = 255, message = "Icon URL cannot exceed 255 characters")
    private String iconUrl;
    
    @Size(max = 255, message = "APK URL cannot exceed 255 characters")
    private String apkUrl;
}
