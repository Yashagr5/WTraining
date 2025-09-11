package com.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.company.client.UserServiceClient;
import com.company.dto.OwnerNotifyDto;
import com.company.entity.Owner;
import com.company.exception.CustomException;
import com.company.repo.OwnerRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OwnerService {

    private final OwnerRepository ownerRepository;
    private final UserServiceClient userServiceClient;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public OwnerService(OwnerRepository ownerRepository,
                        UserServiceClient userServiceClient,
                        PasswordEncoder passwordEncoder) {
        this.ownerRepository = ownerRepository;
        this.userServiceClient = userServiceClient;
        this.passwordEncoder = passwordEncoder;
    }

    // ✅ Register new owner
    public Owner registerOwner(Owner owner) {
        if (ownerRepository.existsByEmail(owner.getEmail())) {
            throw new CustomException("Email already registered!");
        }
        if (ownerRepository.existsByPhone(owner.getPhone())) {
            throw new CustomException("Phone already registered!");
        }
        if (ownerRepository.existsByOwnerName(owner.getOwnerName())) {
            throw new CustomException("Owner name already exists!");
        }

        // Set approval status to APPROVED for all new registrations
        owner.setApprovalStatus(Owner.ApprovalStatus.APPROVED);
        if (owner.getIsActive() == null) {
            owner.setIsActive(true);
        }

        // Encrypt password
        owner.setPassword(passwordEncoder.encode(owner.getPassword()));
        Owner saved = ownerRepository.save(owner);

        // Notify user-service via Feign
        notifyUser(saved.getOwnerId(), saved.getOwnerName());

        return saved;
    }

    // JWT Authentication methods
    public boolean existsByOwnerName(String ownerName) {
        return ownerRepository.existsByOwnerName(ownerName);
    }

    public boolean existsByEmail(String email) {
        return ownerRepository.existsByEmail(email);
    }

    public boolean existsByPhone(String phone) {
        return ownerRepository.existsByPhone(phone);
    }

    public Owner findByOwnerNameOrEmail(String usernameOrEmail) {
        return ownerRepository.findByOwnerNameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new CustomException("Owner not found"));
    }

    public void addNameAndNotify(int ownerId, String ownerName) {
        notifyUser(ownerId, ownerName);
    }

    private void notifyUser(Integer ownerId, String ownerName) {
        try {
            OwnerNotifyDto dto = new OwnerNotifyDto(ownerId, ownerName);
            String response = userServiceClient.notifyUserService(dto);
            log.info("✅ User-service responded: {}", response);
        } catch (Exception ex) {
            log.error("❌ Feign notify failed while calling user-service", ex);
        }
    }

    // ✅ Login
    public Owner login(String identifier, String password) {
        Owner owner = ownerRepository.findByEmailOrPhone(identifier)
                .orElseThrow(() -> new CustomException("Owner not found with given identifier."));

        if (!owner.getIsActive()) {
            throw new CustomException("Owner account is deactivated.");
        }

        if (!passwordEncoder.matches(password, owner.getPassword())) {
            throw new CustomException("Invalid password!");
        }

        return owner;
    }
}
