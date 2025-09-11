package com.company.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // Find user by username
    Optional<User> findByUserName(String userName);

    // Find user by email
    Optional<User> findByEmail(String email);

    // Find user by phone
    Optional<User> findByPhone(String phone);

    // Find user by username or email (for JWT authentication)
    Optional<User> findByUserNameOrEmail(String userName, String email);

    // Check if a user exists by username
    boolean existsByUserName(String userName);

    // Check if a user exists by email
    boolean existsByEmail(String email);

    // Check if a user exists by phone
    boolean existsByPhone(String phone);
    
    default Optional<User> findByEmailOrPhone(String identifier) {
        Optional<User> user = findByEmail(identifier);
        if (user.isPresent()) {
            return user;
        }
        return findByPhone(identifier);
    }
}
