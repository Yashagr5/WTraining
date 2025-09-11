package com.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppFeatureRequest {
    
    private boolean featured;
    private String category;
    private int duration; // days to feature
}
