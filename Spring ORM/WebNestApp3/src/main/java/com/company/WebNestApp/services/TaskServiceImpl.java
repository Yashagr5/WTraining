package com.company.WebNestApp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.WebNestApp.dao.TaskDao;
import com.company.WebNestApp.model.Task;

@Service
public class TaskServiceImpl implements TaskService{
	@Autowired
    private TaskDao taskDao;

    @Override
    public Integer add(Task task) { 
        return taskDao.save(task); 
    }

    @Override
    public void update(Task task) 
    { 
    	taskDao.update(task); 
    }

    @Override
    public void delete(Integer id) 
    { 
    	taskDao.delete(id); 
    }

    @Override
    public Task get(Integer id) 
    { 
    	return taskDao.findById(id); 
    }

    @Override
    public List<Task> listAll() 
    { 
    	return taskDao.findAll(); 
    }

    @Override
    public List<Task> listByUser(Integer userId) 
    { 
    	return taskDao.findByUser(userId); 
    }
    
 // Add these methods to your existing TaskServiceImpl class

    @Override
    public List<Task> listByStatus(String status) {
        return taskDao.findByStatus(status);
    }

    @Override
    public List<Task> listByAssignedBy(Integer adminId) {
        return taskDao.findByAssignedBy(adminId);
    }
}
