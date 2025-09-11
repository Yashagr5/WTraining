package com.company.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.dto.AppDTO;
import com.company.service.AppService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/apps")
@RequiredArgsConstructor
public class AppController {

    private final AppService appService;

    @PostMapping
    public ResponseEntity<AppDTO> createApp(@RequestBody AppDTO appDTO) {
        return ResponseEntity.ok(appService.createApp(appDTO));
    }

    @GetMapping
    public ResponseEntity<List<AppDTO>> getAllApps() {
        return ResponseEntity.ok(appService.getAllApps());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppDTO> getAppById(@PathVariable Long id) {
        return ResponseEntity.ok(appService.getAppById(id));
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<AppDTO>> getAppsByOwner(@PathVariable Long ownerId) {
        return ResponseEntity.ok(appService.getAppsByOwner(ownerId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<AppDTO>> searchApps(@RequestParam String keyword) {
        return ResponseEntity.ok(appService.searchApps(keyword));
    }
    
    @PutMapping("/{appId}/increment-download")
    public ResponseEntity<Void> incrementDownload(@PathVariable Long appId) {
        appService.incrementDownload(appId);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<AppDTO> updateApp(@PathVariable Long id, @RequestBody AppDTO appDTO) {
        appDTO.setId(id);
        return ResponseEntity.ok(appService.updateApp(appDTO));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApp(@PathVariable Long id) {
        appService.deleteApp(id);
        return ResponseEntity.ok().build();
    }
}
