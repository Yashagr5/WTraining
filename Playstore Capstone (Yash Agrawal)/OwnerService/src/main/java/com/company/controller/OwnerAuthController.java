package com.company.controller;

import java.util.HashMap;
import java.util.Map;

import com.company.dto.OwnerLoginRequest;
import com.company.dto.OwnerLoginResponse;
import com.company.dto.OwnerRegisterRequest;
import com.company.entity.Owner;
import com.company.security.JwtTokenProvider;
import com.company.service.OwnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/owners")
@RequiredArgsConstructor

public class OwnerAuthController {
    
    private final AuthenticationManager authenticationManager;
    private final OwnerService ownerService;
    private final JwtTokenProvider tokenProvider;
    
    @PostMapping("/register")
    public ResponseEntity<?> registerOwner(@Valid @RequestBody OwnerRegisterRequest registerRequest) {
        try {
            // Check if owner name already exists
            if (ownerService.existsByOwnerName(registerRequest.getOwnerName())) {
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("success", false);
                errorResponse.put("message", "Owner name is already taken!");
                errorResponse.put("field", "ownerName");
                return ResponseEntity.badRequest().body(errorResponse);
            }
            
            // Check if email already exists
            if (ownerService.existsByEmail(registerRequest.getEmail())) {
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("success", false);
                errorResponse.put("message", "Email is already in use!");
                errorResponse.put("field", "email");
                return ResponseEntity.badRequest().body(errorResponse);
            }
            
            // Check if phone already exists
            if (ownerService.existsByPhone(registerRequest.getPhone())) {
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("success", false);
                errorResponse.put("message", "Phone number is already in use!");
                errorResponse.put("field", "phone");
                return ResponseEntity.badRequest().body(errorResponse);
            }
            
            // Create new owner
            Owner owner = Owner.builder()
                    .ownerName(registerRequest.getOwnerName())
                    .email(registerRequest.getEmail())
                    .phone(registerRequest.getPhone())
                    .password(registerRequest.getPassword())
                    .companyName(registerRequest.getCompanyName())
                    .businessAddress(registerRequest.getBusinessAddress())
                    .businessType(registerRequest.getBusinessType())
                    .approvalStatus(Owner.ApprovalStatus.PENDING)  // Set default approval status
                    .isActive(true)  // Set default active status
                    .build();
            
            Owner savedOwner = ownerService.registerOwner(owner);
            
            Map<String, Object> successResponse = new HashMap<>();
            successResponse.put("success", true);
            successResponse.put("message", "Owner registered successfully");
            Map<String, Object> data = new HashMap<>();
            data.put("ownerId", savedOwner.getOwnerId());
            data.put("ownerName", savedOwner.getOwnerName());
            data.put("approvalStatus", savedOwner.getApprovalStatus().toString());
            successResponse.put("data", data);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
            
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Registration failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> authenticateOwner(@Valid @RequestBody OwnerLoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsernameOrEmail(),
                            loginRequest.getPassword()
                    )
            );
            
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            String jwt = tokenProvider.generateToken(authentication);
            
            // Get owner details
            Owner owner = ownerService.findByOwnerNameOrEmail(loginRequest.getUsernameOrEmail());
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Login successful");
            
            Map<String, Object> data = new HashMap<>();
            data.put("token", jwt);
            data.put("ownerId", owner.getOwnerId());
            data.put("ownerName", owner.getOwnerName());
            data.put("email", owner.getEmail());
            data.put("companyName", owner.getCompanyName());
            data.put("role", "OWNER");
            data.put("approvalStatus", owner.getApprovalStatus().name());
            
            response.put("data", data);
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Invalid username/email or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }
    
    @GetMapping("/profile")
    public ResponseEntity<?> getOwnerProfile(Authentication authentication) {
        try {
            String ownerName = authentication.getName();
            Owner owner = ownerService.findByOwnerNameOrEmail(ownerName);
            
            return ResponseEntity.ok(owner);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: Owner not found");
        }
    }
    
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Owner Service is running with JWT authentication");
    }
}
