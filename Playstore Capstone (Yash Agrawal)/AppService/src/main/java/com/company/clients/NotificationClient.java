package com.company.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.company.dto.NotificationRequestDTO;

@FeignClient(name = "notification-ms")
public interface NotificationClient {

    @PostMapping("/api/notifications/app-published")
    String notifyAppPublished(@RequestBody NotificationRequestDTO request);
    
    @PostMapping("/api/notifications/app-updated")
    String notifyAppUpdated(@RequestBody NotificationRequestDTO request);
    
    @PostMapping("/api/notifications/app-approved")
    String notifyAppApproved(@RequestBody NotificationRequestDTO request);
    
    @PostMapping("/api/notifications/app-rejected")
    String notifyAppRejected(@RequestBody NotificationRequestDTO request);
}
