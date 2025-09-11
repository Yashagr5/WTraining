package com.company.dto;

import java.time.LocalDateTime;

public record CommentDTO(
    Long id,
    String commentText,
    LocalDateTime createdAt,
    UserDTO user
) {}