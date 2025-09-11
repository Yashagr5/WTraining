package com.company.WebNestApp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.company.WebNestApp.dao.TaskDao;
import com.company.WebNestApp.model.Task;

@Service
public class TaskService {
	
private final TaskDao dao;

public TaskService(TaskDao dao) {
	this.dao = dao;
}
public void saveTask(Task task) {
    dao.saveTask(task);
}
public Task findById(Long id) {
    return dao.findById(id);
}

public List<Task> findByAssignedUserId(Long userId) {
    return dao.findByAssignedUserId(userId);
}

public List<Task> findByStatus(String status) {
    return dao.findByStatus(status);
}

public void updateTask(Task task) {
    dao.updateTask(task);
}

public void deleteTask(Long id) {
    dao.deleteTask(id);
}
}
