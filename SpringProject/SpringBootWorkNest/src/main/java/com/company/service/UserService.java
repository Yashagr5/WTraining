package com.company.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.company.model.User;
import com.company.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ✅ Register new user with encoded password
    public User registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // 🔎 Find user by ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    
    // Get user by ID ✅ (fix for your error)
    public User getUsersById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null); // return null if not found
    }
    
    // 🔎 Find user by email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // 🔎 Find user by name
    public Optional<User> getUserByName(String name) {
        return userRepository.findByName(name);
    }

    // 🔎 Find users by role
    public List<User> getUsersByRole(User.Role role) {
        return userRepository.findAll()
                .stream()
                .filter(u -> u.getRole() == role)
                .toList();
    }

    // 📋 Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ✏️ Update user
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    // 🔐 Load user for Spring Security login
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().name()) // "USER" or "ADMIN"
                .build();
    }

    // ✅ Login helper (optional)
    public User login(String email, String rawPassword) {
        User user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(rawPassword, user.getPassword())) {
            return user;
        }
        return null;
    }
}
