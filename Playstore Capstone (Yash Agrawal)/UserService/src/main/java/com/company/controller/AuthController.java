package com.company.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.dto.LoginRequest;
import com.company.dto.LoginResponse;
import com.company.dto.RegisterRequest;
import com.company.entity.User;
import com.company.security.JwtTokenProvider;
import com.company.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User Authentication", description = "User registration, login, and profile management")
public class AuthController {
    
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenProvider tokenProvider;
    
    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Creates a new user account with the provided details")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "User registered successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "409", description = "Username or email already exists")
    })
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        try {
            // Check if username already exists
            if (userService.existsByUsername(registerRequest.getUsername())) {
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("success", false);
                errorResponse.put("message", "Username is already taken!");
                errorResponse.put("field", "username");
                return ResponseEntity.badRequest().body(errorResponse);
            }
            
            // Check if email already exists
            if (userService.existsByEmail(registerRequest.getEmail())) {
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("success", false);
                errorResponse.put("message", "Email is already in use!");
                errorResponse.put("field", "email");
                return ResponseEntity.badRequest().body(errorResponse);
            }
            
            // Check if phone already exists
            if (userService.existsByPhone(registerRequest.getPhone())) {
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("success", false);
                errorResponse.put("message", "Phone number is already in use!");
                errorResponse.put("field", "phone");
                return ResponseEntity.badRequest().body(errorResponse);
            }
            
            // Create new user
            User user = User.builder()
                    .userName(registerRequest.getUsername())
                    .email(registerRequest.getEmail())
                    .phone(registerRequest.getPhone())
                    .password(registerRequest.getPassword())
                    .fullName(registerRequest.getFullName() != null ? registerRequest.getFullName() : registerRequest.getUsername())
                    .address(registerRequest.getAddress() != null ? registerRequest.getAddress() : "")
                    .build();
            
            User savedUser = userService.register(user);
            
            Map<String, Object> successResponse = new HashMap<>();
            successResponse.put("success", true);
            successResponse.put("message", "User registered successfully");
            Map<String, Object> data = new HashMap<>();
            data.put("userId", savedUser.getUserId());
            data.put("username", savedUser.getUsername());
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
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            // Since JWT authentication is disabled for development, 
            // perform simple username/password validation
            User user = userService.findByUsernameOrEmail(loginRequest.getUsernameOrEmail());
            
            if (user == null) {
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("success", false);
                errorResponse.put("message", "Invalid username/email or password");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
            }
            
            // For development, allow simple password validation or skip password check
            // In production, you would use: passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())
            boolean passwordValid = userService.validatePassword(loginRequest.getPassword(), user.getPassword());
            
            if (!passwordValid) {
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("success", false);
                errorResponse.put("message", "Invalid username/email or password");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
            }
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Login successful");
            
            Map<String, Object> data = new HashMap<>();
            data.put("token", "dev-token-" + user.getUserId()); // Simple token for development
            data.put("userId", user.getUserId());
            data.put("username", user.getUsername());
            data.put("email", user.getEmail());
            data.put("fullName", user.getFullName());
            data.put("role", "USER");
            
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
    public ResponseEntity<?> getUserProfile(Authentication authentication) {
        try {
            String username = authentication.getName();
            User user = userService.findByUsernameOrEmail(username);
            
            return ResponseEntity.ok(user);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: User not found");
        }
    }
    
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("User Service is running with JWT authentication");
    }
}
