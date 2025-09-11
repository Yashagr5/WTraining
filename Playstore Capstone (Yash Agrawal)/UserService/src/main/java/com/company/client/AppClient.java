package com.company.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.dto.AppDTO;

@FeignClient(name = "APP-MS")
public interface AppClient {

    @GetMapping("/api/apps/{id}")
    AppDTO getAppById(@PathVariable("id") Integer id);
    
    @GetMapping("/api/apps/search")
    List<AppDTO> searchApps(@RequestParam("searchTerm") String searchTerm);
    
    @GetMapping("/api/apps/category/{categoryId}")
    List<AppDTO> getAppsByCategory(@PathVariable("categoryId") Integer categoryId);
    
    @GetMapping("/api/apps/featured")
    List<AppDTO> getFeaturedApps();
    
    @PostMapping("/api/apps/{id}/download")
    String incrementDownloadCount(@PathVariable("id") Integer id);
}
