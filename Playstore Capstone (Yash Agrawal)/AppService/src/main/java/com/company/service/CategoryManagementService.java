package com.company.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.dto.CategoryDTO;
import com.company.entity.Category;
import com.company.repo.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryManagementService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public CategoryDTO createCategory(CategoryDTO dto) {
        // Check if category name already exists
        if (categoryRepository.existsByName(dto.getName())) {
            throw new RuntimeException("Category name already exists");
        }

        // Build entity from DTO
        Category category = Category.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .isFeatured(dto.getIsFeatured() != null ? dto.getIsFeatured() : false)
                .appCount(0L)
                .build();

        // Save and return as DTO
        return toDTO(categoryRepository.save(category));
    }

    @Transactional(readOnly = true)
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + id));
        return toDTO(category);
    }

    // Convert entity to DTO
    private CategoryDTO toDTO(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .appCount(category.getAppCount())
                .isFeatured(category.getIsFeatured())
                .build();
    }
} 