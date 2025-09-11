package com.company.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.dto.NotificationDto;
import com.company.entity.Notification;
import com.company.entity.Download;
import com.company.repo.NotificationRepository;
import com.company.repo.DownloadRepository;
import com.company.clients.OwnerClient;
import com.company.clients.UserClient;
import com.company.dto.OwnerDTO;
import com.company.dto.UserDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationManagementService {

    private final NotificationRepository notificationRepository;
    private final DownloadRepository downloadRepository;
    private final OwnerClient ownerClient;
    private final UserClient userClient;

    @Transactional
    public Notification sendNotificationToOwner(Long ownerId, String title, String description) {
        try {
            OwnerDTO owner = ownerClient.getOwnerById(ownerId);
            
            Notification notification = Notification.builder()
                    .recipientId(ownerId)
                    .title(title)
                    .description(description)
                    .recipientEmail(owner.getEmail())
                    .notificationDate(LocalDate.now())
                    .notificationType("OWNER")
                    .isSent(true)
                    .sentDate(LocalDate.now())
                    .build();

            return notificationRepository.save(notification);
        } catch (Exception e) {
            // If owner service is not available, still save the notification
            Notification notification = Notification.builder()
                    .recipientId(ownerId)
                    .title(title)
                    .description(description)
                    .notificationDate(LocalDate.now())
                    .notificationType("OWNER")
                    .isSent(false)
                    .build();
            return notificationRepository.save(notification);
        }
    }

    @Transactional
    public Notification sendNotificationToUser(Long userId, String title, String description) {
        try {
            UserDTO user = userClient.getUserById(userId);
            
            Notification notification = Notification.builder()
                    .recipientId(userId)
                    .title(title)
                    .description(description)
                    .recipientEmail(user.getEmail())
                    .notificationDate(LocalDate.now())
                    .notificationType("USER")
                    .isSent(true)
                    .sentDate(LocalDate.now())
                    .build();

            return notificationRepository.save(notification);
        } catch (Exception e) {
            // If user service is not available, still save the notification
            Notification notification = Notification.builder()
                    .recipientId(userId)
                    .title(title)
                    .description(description)
                    .notificationDate(LocalDate.now())
                    .notificationType("USER")
                    .isSent(false)
                    .build();
            return notificationRepository.save(notification);
        }
    }

    @Transactional
    public void notifyOwnerAboutDownload(Long ownerId, Long appId, String appName) {
        String title = "New Download";
        String description = String.format("Your app '%s' has been downloaded!", appName);
        
        Notification notification = Notification.builder()
                .recipientId(ownerId)
                .applicationId(appId)
                .title(title)
                .description(description)
                .notificationDate(LocalDate.now())
                .notificationType("DOWNLOAD")
                .priority("MEDIUM")
                .isSent(true)
                .sentDate(LocalDate.now())
                .build();
                
        notificationRepository.save(notification);
    }

    @Transactional
    public void announceAppUpdate(Long appId, String appName, String updateDescription) {
        // Get all users who have downloaded this app
        List<Long> userIds = downloadRepository.findByApplicationId(appId).stream()
                .map(Download::getUserId)
                .distinct()
                .collect(Collectors.toList());
        
        String title = String.format("Update Available: %s", appName);
        
        // Create notifications for all users
        for (Long userId : userIds) {
            Notification notification = Notification.builder()
                    .recipientId(userId)
                    .applicationId(appId)
                    .title(title)
                    .description(updateDescription)
                    .notificationDate(LocalDate.now())
                    .notificationType("UPDATE")
                    .priority("HIGH")
                    .isSent(false) // Will be sent via batch process or WebSocket
                    .build();
                    
            notificationRepository.save(notification);
        }
    }
    
    @Transactional(readOnly = true)
    public List<NotificationDto> getNotificationsByRecipient(Long recipientId) {
        return notificationRepository.findByRecipientId(recipientId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<NotificationDto> getNotificationsByType(String type) {
        return notificationRepository.findByNotificationType(type).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    
    private NotificationDto toDTO(Notification notification) {
        return NotificationDto.builder()
                .id(notification.getId())
                .applicationId(notification.getApplicationId())
                .ownerId(notification.getNotificationType().equals("OWNER") ? notification.getRecipientId() : null)
                .userId(notification.getNotificationType().equals("USER") ? notification.getRecipientId() : null)
                .title(notification.getTitle())
                .description(notification.getDescription())
                .notificationType(notification.getNotificationType())
                .priority(notification.getPriority())
                .isSent(notification.getIsSent())
                .notificationDate(notification.getNotificationDate())
                .sentDate(notification.getSentDate())
                .recipientEmail(notification.getRecipientEmail())
                .retryCount(notification.getRetryCount())
                .build();
    }
} 