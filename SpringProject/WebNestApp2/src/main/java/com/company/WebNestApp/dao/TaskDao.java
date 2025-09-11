package com.company.WebNestApp.dao;

import java.util.List;

import com.company.WebNestApp.model.Task;

public interface TaskDao {
	List<Task> findByAssignedUserId(Long userId);
    List<Task> findByStatus(String status);
    void saveTask(Task task);
    Task findById(Long id);
}
