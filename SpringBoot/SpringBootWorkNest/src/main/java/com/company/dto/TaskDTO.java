package com.company.dto;

import java.time.LocalDate;
import java.util.List;

public record TaskDTO(
    Long id,
    String title,
    String description,
    String status,
    LocalDate startDate,
    LocalDate dueDate,
    List<UserDTO> assignedUsers,
    List<CommentDTO> comments
) {}
