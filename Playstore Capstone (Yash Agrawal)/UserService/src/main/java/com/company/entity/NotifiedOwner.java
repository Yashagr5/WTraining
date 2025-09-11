package com.company.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notified_owners")
public class NotifiedOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int ownerId;

    private String ownerName;

    private String message; // notification text (e.g., "New Owner registered")

    private LocalDateTime createdAt = LocalDateTime.now(); // timestamp of notification

    public NotifiedOwner() {
        super();
    }
    
    public NotifiedOwner(int ownerId, String ownerName) {
        super();
        this.ownerId = ownerId;
        this.ownerName = ownerName;
    }

    public NotifiedOwner(int ownerId, String ownerName, String message) {
        this.ownerId = ownerId;
        this.ownerName = ownerName;
        this.message = message;
        this.createdAt = LocalDateTime.now();
    }

    // --- Getters & Setters ---
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
