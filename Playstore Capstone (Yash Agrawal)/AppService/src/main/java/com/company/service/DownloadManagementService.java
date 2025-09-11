package com.company.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.dto.DownloadDTO;
import com.company.entity.Download;
import com.company.entity.App;
import com.company.repo.DownloadRepository;
import com.company.repo.AppRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DownloadManagementService {

    private final DownloadRepository downloadRepository;
    private final AppRepository appRepository;
    private final NotificationManagementService notificationService;

    @Transactional
    public DownloadDTO recordDownload(DownloadDTO dto) {
        // Save the download
        Download download = Download.builder()
                .applicationId(dto.getApplicationId())
                .userId(dto.getUserId())
                .downloadDate(dto.getDownloadDate())
                .downloadStatus(dto.getDownloadStatus())
                .deviceInfo(dto.getDeviceInfo())
                .ipAddress(dto.getIpAddress())
                .downloadSizeMb(dto.getDownloadSizeMb())
                .downloadDurationSeconds(dto.getDownloadDurationSeconds())
                .isSuccessful(dto.getIsSuccessful() != null ? dto.getIsSuccessful() : true)
                .errorMessage(dto.getErrorMessage())
                .build();

        Download saved = downloadRepository.save(download);

        // Update app download count
        appRepository.findById(dto.getApplicationId()).ifPresent(app -> {
            app.setDownloadCount(app.getDownloadCount() + 1);
            appRepository.save(app);
            
            // Notify the owner about the download
            notificationService.notifyOwnerAboutDownload(app.getOwnerId(), app.getId(), app.getName());
        });

        return toDTO(saved);
    }

    @Transactional(readOnly = true)
    public List<DownloadDTO> getDownloadsByUser(Long userId) {
        return downloadRepository.findByUserId(userId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<DownloadDTO> getDownloadsByApp(Long appId) {
        return downloadRepository.findByApplicationId(appId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Long getDownloadCountByApp(Long appId) {
        return downloadRepository.countByApplicationId(appId);
    }

    private DownloadDTO toDTO(Download download) {
        return DownloadDTO.builder()
                .id(download.getId())
                .applicationId(download.getApplicationId())
                .userId(download.getUserId())
                .downloadDate(download.getDownloadDate())
                .downloadStatus(download.getDownloadStatus())
                .deviceInfo(download.getDeviceInfo())
                .ipAddress(download.getIpAddress())
                .downloadSizeMb(download.getDownloadSizeMb())
                .downloadDurationSeconds(download.getDownloadDurationSeconds())
                .isSuccessful(download.getIsSuccessful())
                .errorMessage(download.getErrorMessage())
                .build();
    }
} 