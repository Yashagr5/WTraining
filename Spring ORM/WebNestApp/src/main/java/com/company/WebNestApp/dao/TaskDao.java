package com.company.WebNestApp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.company.WebNestApp.model.Task;

@Repository
public interface TaskDao {
	List<Task> findByAssignedUserId(Long userId);
    List<Task> findByStatus(String status);
    void saveTask(Task task);
    Task findById(Long id);
}
