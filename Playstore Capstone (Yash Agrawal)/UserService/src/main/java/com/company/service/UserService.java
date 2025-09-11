package com.company.service;

import com.company.entity.User;
import com.company.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Register a new user
    @Transactional
    public User register(User user) {
        log.info("Registering new user: {}", user.getUsername());
        
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            log.warn("Registration failed: Email already exists - {}", user.getEmail());
            throw new RuntimeException("Email already registered");
        }
        if (userRepository.findByPhone(user.getPhone()).isPresent()) {
            log.warn("Registration failed: Phone already exists - {}", user.getPhone());
            throw new RuntimeException("Phone already registered");
        }
        if (userRepository.existsByUserName(user.getUsername())) {
            log.warn("Registration failed: Username already exists - {}", user.getUsername());
            throw new RuntimeException("Username already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setIsActive(true);
        user.setCreatedAt(LocalDateTime.now());
        
        User savedUser = userRepository.save(user);
        log.info("User registered successfully: {}", savedUser.getUserId());
        return savedUser;
    }

    // JWT Authentication methods
    public boolean existsByUsername(String username) {
        return userRepository.existsByUserName(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean existsByPhone(String phone) {
        return userRepository.existsByPhone(phone);
    }

    public User findByUsernameOrEmail(String usernameOrEmail) {
        return userRepository.findByUserNameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Login with email or phone
    public User login(String identifier, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(identifier);
        if (optionalUser.isEmpty()) {
            optionalUser = userRepository.findByPhone(identifier);
        }

        User user = optionalUser.orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        if (!user.getIsActive()) {
            throw new RuntimeException("User is not active");
        }

        return user;
    }

    // Legacy CRUD methods
    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Cacheable("users")
    public Optional<User> getUserById(Integer id) {
        log.debug("Fetching user by ID: {}", id);
        return userRepository.findById(id);
    }

    @Cacheable(value = "users", key = "#email")
    public Optional<User> getUserByEmail(String email) {
        log.debug("Fetching user by email: {}", email);
        return userRepository.findByEmail(email);
    }

    public Optional<User> getUserByPhone(String phone) {
        log.debug("Fetching user by phone: {}", phone);
        return userRepository.findByPhone(phone);
    }

    @Transactional
    @CacheEvict(value = "users", key = "#id")
    public User updateUser(Integer id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setUserName(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            user.setPhone(updatedUser.getPhone());
            if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            }
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    // Corrected findByEmailOrPhone
    public User findByEmailOrPhone(String identifier) {
        Optional<User> userOpt = userRepository.findByEmailOrPhone(identifier);
        return userOpt.orElseThrow(() -> new RuntimeException("User not found"));
    }
    
    // Password validation for development login
    public boolean validatePassword(String rawPassword, String encodedPassword) {
        // For development, allow both encoded and plain text passwords
        if (encodedPassword.startsWith("$2a$")) {
            // BCrypt encoded password
            return passwordEncoder.matches(rawPassword, encodedPassword);
        } else {
            // Plain text password (for development/testing)
            return rawPassword.equals(encodedPassword);
        }
    }
}
