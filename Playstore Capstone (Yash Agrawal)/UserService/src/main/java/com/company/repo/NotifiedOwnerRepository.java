package com.company.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.entity.NotifiedOwner;

public interface NotifiedOwnerRepository extends JpaRepository<NotifiedOwner, Integer> {
	
	// Fetch all notifications sorted by createdAt descending
    List<NotifiedOwner> findAllByOrderByCreatedAtDesc();
    
}
