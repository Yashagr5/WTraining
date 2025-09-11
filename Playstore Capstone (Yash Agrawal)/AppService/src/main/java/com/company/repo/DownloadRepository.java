package com.company.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.entity.Download;

@Repository
public interface DownloadRepository extends JpaRepository<Download, Long> {
    List<Download> findByUserId(Long userId);
    List<Download> findByApplicationId(Long appId);
    Long countByApplicationId(Long appId);
} 