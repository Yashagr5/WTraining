package com.company.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private Long id;
    private Long applicationId;
    private Long userId;
    private String description;
    private Integer rating;
    private LocalDate createdDate;
    private Boolean isFlagged;
    private Long helpfulCount;
} 