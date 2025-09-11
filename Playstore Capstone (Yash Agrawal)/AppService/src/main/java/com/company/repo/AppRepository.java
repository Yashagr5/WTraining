package com.company.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.company.entity.App;

@Repository
public interface AppRepository extends JpaRepository<App, Long> {
    List<App> findByOwnerId(Long ownerId);
    List<App> findByCategoryId(Long categoryId);
    List<App> findByStatus(String status);
    List<App> findByNameContainingIgnoreCase(String keyword);
    @Query(value = "SELECT DISTINCT tag FROM app_tags", nativeQuery = true)
    List<String> findDistinctTags(int limit);
}
