package com.company.dto;

public record UserDTO(
    Long id,
    String name,
    String email,
    String role
) {}