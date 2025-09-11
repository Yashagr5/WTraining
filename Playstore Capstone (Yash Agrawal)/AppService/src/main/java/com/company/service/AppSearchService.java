package com.company.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.company.clients.CategoryClient;
import com.company.clients.OwnerClient;
import com.company.dto.AppResponseDto;
import com.company.dto.CategoryDTO;
import com.company.dto.OwnerDTO;
import com.company.entity.App;
import com.company.repo.AppRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppSearchService {
    
    private final AppRepository appRepository;
    private final CategoryClient categoryClient;
    private final OwnerClient ownerClient;
    
    public Page<AppResponseDto> advancedSearch(
            String name, Integer categoryId, String categoryName, BigDecimal minRating, BigDecimal maxRating,
            BigDecimal minPrice, BigDecimal maxPrice, String ownerName, Integer ownerId, String status,
            Boolean isFeatured, Boolean isFree, String developerName, String tags,
            Long minDownloads, Long maxDownloads, String sortBy, String sortDirection, Pageable pageable) {
        
        try {
            // Mock implementation - in real scenario, this would use complex repository queries
            List<App> allApps = appRepository.findAll();
            
            List<App> filteredApps = allApps.stream()
                .filter(app -> name == null || app.getName().toLowerCase().contains(name.toLowerCase()))
                .filter(app -> categoryId == null || Objects.equals(app.getCategoryId(), categoryId))
                .filter(app -> minRating == null || app.getRating().compareTo(minRating) >= 0)
                .filter(app -> maxRating == null || app.getRating().compareTo(maxRating) <= 0)
                .filter(app -> minDownloads == null || app.getDownloadCount() >= minDownloads)
                .filter(app -> maxDownloads == null || app.getDownloadCount() <= maxDownloads)
                .filter(app -> status == null ? "Active".equalsIgnoreCase(app.getStatus()) : app.getStatus().equalsIgnoreCase(status)) // Show only Active apps by default, unless specific status requested
                .collect(Collectors.toList());
            
            // Convert to DTOs
            List<AppResponseDto> responseDtos = filteredApps.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
            
            // Apply pagination
            int start = (int) pageable.getOffset();
            int end = Math.min((start + pageable.getPageSize()), responseDtos.size());
            List<AppResponseDto> pageContent = responseDtos.subList(start, end);
            
            return new PageImpl<>(pageContent, pageable, responseDtos.size());
            
        } catch (Exception e) {
            log.error("Error in advanced search", e);
            return new PageImpl<>(Collections.emptyList(), pageable, 0);
        }
    }
    
    public List<String> getAvailableTags(int limit) {
        // Example mock logic: fetch distinct tags
        return appRepository.findDistinctTags(limit);
    }
    
    public Page<AppResponseDto> multiCriteriaSearch(
            String searchTerm, List<Integer> categoryIds, List<String> categoryNames,
            BigDecimal minRating, BigDecimal maxRating, List<String> tags,
            Boolean isFeatured, Boolean isFree, String priceRange, String ratingRange,
            String sortBy, Pageable pageable) {
        
        try {
            List<App> allApps = appRepository.findAll();
            
            List<App> filteredApps = allApps.stream()
                .filter(app -> "Active".equalsIgnoreCase(app.getStatus())) // Only show visible apps
                .filter(app -> searchTerm == null || 
                    app.getName().toLowerCase().contains(searchTerm.toLowerCase()) ||
                    app.getDescription().toLowerCase().contains(searchTerm.toLowerCase()))
                .filter(app -> categoryIds == null || categoryIds.isEmpty() || categoryIds.contains(app.getCategoryId()))
                .filter(app -> minRating == null || app.getRating().compareTo(minRating) >= 0)
                .filter(app -> maxRating == null || app.getRating().compareTo(maxRating) <= 0)
                .collect(Collectors.toList());
            
            List<AppResponseDto> responseDtos = filteredApps.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
            
            int start = (int) pageable.getOffset();
            int end = Math.min((start + pageable.getPageSize()), responseDtos.size());
            List<AppResponseDto> pageContent = responseDtos.subList(start, end);
            
            return new PageImpl<>(pageContent, pageable, responseDtos.size());
            
        } catch (Exception e) {
            log.error("Error in multi-criteria search", e);
            return new PageImpl<>(Collections.emptyList(), pageable, 0);
        }
    }
    
    public List<String> getSearchSuggestions(String query, int limit) {
        try {
            List<App> apps = appRepository.findAll();
            
            return apps.stream()
                .map(App::getName)
                .filter(name -> name.toLowerCase().contains(query.toLowerCase()))
                .distinct()
                .limit(limit)
                .collect(Collectors.toList());
                
        } catch (Exception e) {
            log.error("Error getting search suggestions", e);
            return Collections.emptyList();
        }
    }
    
    public List<AppResponseDto> getTrendingApps(int limit, int days) {
        try {
            // Mock trending logic - in real scenario, this would analyze download patterns
            List<App> apps = appRepository.findAll();
            
            return apps.stream()
                .filter(app -> "Active".equalsIgnoreCase(app.getStatus())) // Only show visible apps
                .sorted((a, b) -> Long.compare(b.getDownloadCount(), a.getDownloadCount()))
                .limit(limit)
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
                
        } catch (Exception e) {
            log.error("Error getting trending apps", e);
            return Collections.emptyList();
        }
    }
    
    public List<AppResponseDto> getSimilarApps(Integer appId, int limit) {
        try {
            Optional<App> targetApp = appRepository.findById(Long.valueOf(appId));
            if (targetApp.isEmpty()) {
                return Collections.emptyList();
            }
            
            App app = targetApp.get();
            List<App> similarApps = appRepository.findAll().stream()
                .filter(a -> !a.getId().equals(app.getId()))
                .filter(a -> Objects.equals(a.getCategoryId(), app.getCategoryId()))
                .limit(limit)
                .collect(Collectors.toList());
            
            return similarApps.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
                
        } catch (Exception e) {
            log.error("Error getting similar apps", e);
            return Collections.emptyList();
        }
    }
    
    public List<AppResponseDto> getRecommendedApps(Integer userId, int limit) {
        try {
            // Mock recommendation logic - in real scenario, this would use user behavior analysis
            List<App> apps = appRepository.findAll();
            
            return apps.stream()
                .filter(app -> app.getRating().compareTo(BigDecimal.valueOf(4.0)) >= 0)
                .sorted((a, b) -> b.getRating().compareTo(a.getRating()))
                .limit(limit)
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
                
        } catch (Exception e) {
            log.error("Error getting recommended apps", e);
            return Collections.emptyList();
        }
    }
    
    public List<Object[]> getAvailableCategories() {
        try {
            // In real implementation, this would call CategoryService
            List<Object[]> categories = new ArrayList<>();
            categories.add(new Object[]{1, "Games", 25});
            categories.add(new Object[]{2, "Productivity", 15});
            categories.add(new Object[]{3, "Social", 12});
            categories.add(new Object[]{4, "Education", 18});
            categories.add(new Object[]{5, "Health", 8});
            
            return categories;
            
        } catch (Exception e) {
            log.error("Error getting available categories", e);
            return Collections.emptyList();
        }
    }
    
    public List<String> getPopularTags(int limit) {
        try {
            // Mock popular tags
            return Arrays.asList(
                "productivity", "games", "social", "education", "health",
                "entertainment", "business", "travel", "photography", "music"
            ).stream().limit(limit).collect(Collectors.toList());
            
        } catch (Exception e) {
            log.error("Error getting popular tags", e);
            return Collections.emptyList();
        }
    }
    
    public List<AppResponseDto> getAppsByCategory(Integer categoryId, int limit) {
        try {
            List<App> apps = appRepository.findAll();
            
            return apps.stream()
                .filter(app -> "Active".equalsIgnoreCase(app.getStatus())) // Only show visible apps
                .filter(app -> Objects.equals(app.getCategoryId(), Long.valueOf(categoryId))) // Convert Integer to Long
                .limit(limit)
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
                
        } catch (Exception e) {
            log.error("Error getting apps by category {}", categoryId, e);
            return Collections.emptyList();
        }
    }
    
    private AppResponseDto convertToResponseDto(App app) {
        AppResponseDto dto = new AppResponseDto();
        dto.setId(app.getId());
        dto.setName(app.getName());
        dto.setDescription(app.getDescription());
        dto.setVersion(app.getVersion());
        dto.setStatus(app.getStatus());
        dto.setCategoryId(app.getCategoryId());
        dto.setOwnerId(app.getOwnerId());
        dto.setRating(app.getRating());
        dto.setDownloadCount(app.getDownloadCount());
        dto.setUploadDate(app.getUploadDate());
        dto.setReleaseDate(app.getReleaseDate());
        dto.setIconUrl(app.getIconUrl());
        dto.setApkUrl(app.getApkUrl());
        
        // Enrich with category and owner information via Feign clients
        try {
            if (app.getCategoryId() != null) {
                CategoryDTO category = categoryClient.getCategoryById(app.getCategoryId());
                dto.setCategoryName(category != null ? category.getName() : "Unknown");
            }
        } catch (Exception e) {
            log.warn("Failed to get category info for app {}", app.getId());
            dto.setCategoryName("Unknown");
        }
        
        try {
            if (app.getOwnerId() != null) {
                OwnerDTO owner = ownerClient.getOwnerById(app.getOwnerId());
                dto.setOwnerName(owner != null ? owner.getOwnerName() : "Unknown");
            }
        } catch (Exception e) {
            log.warn("Failed to get owner info for app {}", app.getId());
            dto.setOwnerName("Unknown");
        }
        
        return dto;
    }
}
