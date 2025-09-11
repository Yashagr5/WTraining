package com.company.controller;

import com.company.dto.AppResponseDto;
import com.company.service.AppSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/apps/search")
@RequiredArgsConstructor

public class AppSearchController {
    
    private final AppSearchService appSearchService;
    
    @GetMapping("/advanced")
    public ResponseEntity<Page<AppResponseDto>> advancedSearch(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String categoryName,
            @RequestParam(required = false) BigDecimal minRating,
            @RequestParam(required = false) BigDecimal maxRating,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) String ownerName,
            @RequestParam(required = false) Integer ownerId,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Boolean isFeatured,
            @RequestParam(required = false) Boolean isFree,
            @RequestParam(required = false) String developerName,
            @RequestParam(required = false) String tags,
            @RequestParam(required = false) Long minDownloads,
            @RequestParam(required = false) Long maxDownloads,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDirection,
            Pageable pageable) {
        
        Page<AppResponseDto> apps = appSearchService.advancedSearch(
            name, categoryId, categoryName, minRating, maxRating, 
            minPrice, maxPrice, ownerName, ownerId, status, 
            isFeatured, isFree, developerName, tags, 
            minDownloads, maxDownloads, sortBy, sortDirection, pageable
        );
        
        return ResponseEntity.ok(apps);
    }
    
    @GetMapping("/multi-criteria")
    public ResponseEntity<Page<AppResponseDto>> multiCriteriaSearch(
            @RequestParam(required = false) String searchTerm,
            @RequestParam(required = false) List<Integer> categoryIds,
            @RequestParam(required = false) List<String> categoryNames,
            @RequestParam(required = false) BigDecimal minRating,
            @RequestParam(required = false) BigDecimal maxRating,
            @RequestParam(required = false) List<String> tags,
            @RequestParam(required = false) Boolean isFeatured,
            @RequestParam(required = false) Boolean isFree,
            @RequestParam(required = false) String priceRange, // "free", "paid", "0-5", "5-10", etc.
            @RequestParam(required = false) String ratingRange, // "4+", "3-4", "2-3", etc.
            @RequestParam(defaultValue = "relevance") String sortBy,
            Pageable pageable) {
        
        Page<AppResponseDto> apps = appSearchService.multiCriteriaSearch(
            searchTerm, categoryIds, categoryNames, minRating, maxRating,
            tags, isFeatured, isFree, priceRange, ratingRange, sortBy, pageable
        );
        
        return ResponseEntity.ok(apps);
    }
    
    @GetMapping("/suggestions")
    public ResponseEntity<List<String>> getSearchSuggestions(
            @RequestParam String query,
            @RequestParam(defaultValue = "10") int limit) {
        
        List<String> suggestions = appSearchService.getSearchSuggestions(query, limit);
        return ResponseEntity.ok(suggestions);
    }
    
    @GetMapping("/trending")
    public ResponseEntity<List<AppResponseDto>> getTrendingApps(
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "7") int days) {
        
        List<AppResponseDto> apps = appSearchService.getTrendingApps(limit, days);
        return ResponseEntity.ok(apps);
    }
    
    @GetMapping("/similar/{appId}")
    public ResponseEntity<List<AppResponseDto>> getSimilarApps(
            @PathVariable Integer appId,
            @RequestParam(defaultValue = "5") int limit) {
        
        List<AppResponseDto> apps = appSearchService.getSimilarApps(appId, limit);
        return ResponseEntity.ok(apps);
    }
    
    @GetMapping("/recommendations/{userId}")
    public ResponseEntity<List<AppResponseDto>> getRecommendedApps(
            @PathVariable Integer userId,
            @RequestParam(defaultValue = "10") int limit) {
        
        List<AppResponseDto> apps = appSearchService.getRecommendedApps(userId, limit);
        return ResponseEntity.ok(apps);
    }
    
    @GetMapping("/filters/categories")
    public ResponseEntity<List<Object[]>> getAvailableCategories() {
        List<Object[]> categories = appSearchService.getAvailableCategories();
        return ResponseEntity.ok(categories);
    }
    
    @GetMapping("/filters/price-ranges")
    public ResponseEntity<List<String>> getAvailablePriceRanges() {
        List<String> ranges = List.of("Free", "0-1", "1-5", "5-10", "10-20", "20+");
        return ResponseEntity.ok(ranges);
    }
    
    @GetMapping("/filters/rating-ranges")
    public ResponseEntity<List<String>> getAvailableRatingRanges() {
        List<String> ranges = List.of("4.5+", "4.0+", "3.5+", "3.0+", "2.5+", "All");
        return ResponseEntity.ok(ranges);
    }
    
    @GetMapping("/filters/tags")
    public ResponseEntity<List<String>> getPopularTags(
            @RequestParam(defaultValue = "20") int limit) {
        
        List<String> tags = appSearchService.getAvailableTags(limit);
        return ResponseEntity.ok(tags);
    }
    
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<AppResponseDto>> getAppsByCategory(
            @PathVariable Integer categoryId,
            @RequestParam(defaultValue = "20") int limit) {
        
        List<AppResponseDto> apps = appSearchService.getAppsByCategory(categoryId, limit);
        return ResponseEntity.ok(apps);
    }
}
