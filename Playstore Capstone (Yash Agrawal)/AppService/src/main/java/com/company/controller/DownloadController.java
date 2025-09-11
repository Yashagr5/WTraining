package com.company.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.company.dto.DownloadDTO;
import com.company.dto.TrackDownloadRequest;
import com.company.service.DownloadManagementService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/downloads")
@RequiredArgsConstructor
@Tag(name = "Download API", description = "Endpoints for tracking app downloads")
public class DownloadController {

    private final DownloadManagementService downloadService;

    @Operation(summary = "Record a new download", description = "Records a new app download and notifies the owner")
    @PostMapping
    public ResponseEntity<DownloadDTO> recordDownload(@RequestBody DownloadDTO dto) {
        return ResponseEntity.ok(downloadService.recordDownload(dto));
    }

    @Operation(summary = "Track a download", description = "Tracks a new app download with simplified input")
    @PostMapping("/track")
    public ResponseEntity<DownloadDTO> trackDownload(@RequestBody TrackDownloadRequest request) {
        DownloadDTO dto = DownloadDTO.builder()
                .applicationId(request.getAppId())
                .userId(request.getUserId())
                .downloadDate(java.time.LocalDate.now())
                .downloadStatus("COMPLETED")
                .isSuccessful(true)
                .build();
        return ResponseEntity.ok(downloadService.recordDownload(dto));
    }

    @Operation(summary = "Get downloads by user", description = "Returns all downloads by a specific user")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<DownloadDTO>> getDownloadsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(downloadService.getDownloadsByUser(userId));
    }

    @Operation(summary = "Get downloads by app", description = "Returns all downloads for a specific app")
    @GetMapping("/app/{appId}")
    public ResponseEntity<List<DownloadDTO>> getDownloadsByApp(@PathVariable Long appId) {
        return ResponseEntity.ok(downloadService.getDownloadsByApp(appId));
    }

    @Operation(summary = "Get download count for app", description = "Returns the total download count for a specific app")
    @GetMapping("/app/{appId}/count")
    public ResponseEntity<Long> getDownloadCountByApp(@PathVariable Long appId) {
        return ResponseEntity.ok(downloadService.getDownloadCountByApp(appId));
    }
} 