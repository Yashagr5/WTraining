package com.company.WebNestApp.dao;

import java.util.List;

import com.company.WebNestApp.model.Task;

public interface TaskDao {
	Integer save(Task task);
    void update(Task task);
    void delete(Integer id);
    Task findById(Integer id);
    List<Task> findAll();
    List<Task> findByUser(Integer userId);
    List<Task> findByStatus(String status); // Add this method
    List<Task> findByAssignedBy(Integer adminId); // Add this method
}
