package com.company.WebNest.dao;

import java.util.List;

import com.company.WebNest.model.Task;

public interface TaskDao {
	List<Task> findByAssignedUserId(Long userId);
    List<Task> findByStatus(String status);
    void saveTask(Task task);
    Task findById(Long id);	
    void deleteTask(Long id);
    void updateTask(Task task);
}
