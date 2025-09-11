package com.company.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.dto.AppDTO;
import com.company.entity.App;
import com.company.repo.AppRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppService {

    private final AppRepository appRepository;
    private final NotificationManagementService notificationService;

    @Transactional
    public AppDTO createApp(AppDTO dto) {
        App app = App.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .version(dto.getVersion())
                .uploadDate(dto.getUploadDate())
                .releaseDate(dto.getReleaseDate())
                .status(dto.getStatus())
                .categoryId(dto.getCategoryId())
                .ownerId(dto.getOwnerId())
                .rating(dto.getRating())
                .iconUrl(dto.getIconUrl())
                .apkUrl(dto.getApkUrl())
                .downloadCount(dto.getDownloadCount())
                .build();

        return toDTO(appRepository.save(app));
    }
    
    @Transactional
    public void incrementDownload(Long appId) {
        App app = appRepository.findById(appId)
                .orElseThrow(() -> new RuntimeException("App not found"));
        app.setDownloadCount(app.getDownloadCount() + 1);
        appRepository.save(app);
    }

    public AppDTO getAppById(Long id) {
        return appRepository.findById(id).map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("App not found"));
    }

    public List<AppDTO> getAppsByOwner(Long ownerId) {
        return appRepository.findByOwnerId(ownerId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<AppDTO> getAllApps() {
        return appRepository.findAll().stream()
                .filter(app -> "Active".equalsIgnoreCase(app.getStatus())) // Only show active apps
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<AppDTO> searchApps(String keyword) {
        return appRepository.findByNameContainingIgnoreCase(keyword).stream()
                .filter(app -> "Active".equalsIgnoreCase(app.getStatus())) // Only show active/visible apps
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public AppDTO updateApp(AppDTO appDTO) {
        App app = appRepository.findById(appDTO.getId())
                .orElseThrow(() -> new RuntimeException("App not found with ID: " + appDTO.getId()));
        
        // Update fields
        app.setName(appDTO.getName());
        app.setDescription(appDTO.getDescription());
        app.setVersion(appDTO.getVersion());
        // Only update status if provided, otherwise keep existing status
        if (appDTO.getStatus() != null && !appDTO.getStatus().trim().isEmpty()) {
            app.setStatus(appDTO.getStatus());
        }
        app.setCategoryId(appDTO.getCategoryId());
        app.setRating(appDTO.getRating());
        app.setIconUrl(appDTO.getIconUrl());
        app.setApkUrl(appDTO.getApkUrl());
        
        App updated = appRepository.save(app);
        
        // Announce update to users who have downloaded the app
        if (notificationService != null) {
            notificationService.announceAppUpdate(updated.getId(), updated.getName(), 
                "Version " + updated.getVersion() + " is now available with new features and improvements!");
        }
        
        return toDTO(updated);
    }
    
    @Transactional
    public void deleteApp(Long id) {
        App app = appRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("App not found with ID: " + id));
        
        // Delete related data (comments, downloads, notifications) will be handled by cascade or manually
        appRepository.delete(app);
    }

    private AppDTO toDTO(App app) {
        return AppDTO.builder()
                .id(app.getId())
                .name(app.getName())
                .description(app.getDescription())
                .version(app.getVersion())
                .uploadDate(app.getUploadDate())
                .releaseDate(app.getReleaseDate())
                .status(app.getStatus())
                .categoryId(app.getCategoryId())
                .ownerId(app.getOwnerId())
                .rating(app.getRating())
                .iconUrl(app.getIconUrl())
                .apkUrl(app.getApkUrl())
                .downloadCount(app.getDownloadCount())
                .build();
    }
}
