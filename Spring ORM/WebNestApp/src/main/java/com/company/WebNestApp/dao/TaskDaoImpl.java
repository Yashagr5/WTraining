package com.company.WebNestApp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.company.WebNestApp.model.Task;

@Repository
public class TaskDaoImpl implements TaskDao{
	@Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Task> findByAssignedUserId(Long userId) {
        return getSession()
                .createQuery("from Task where user.id = :userId", Task.class)
                .setParameter("userId", userId)
                .list();
    }

    @Override
    public List<Task> findByStatus(String status) {
        return getSession()
                .createQuery("from Task where status = :status", Task.class)
                .setParameter("status", status)
                .list();
    }

    @Override
    public void saveTask(Task task) {
        getSession().saveOrUpdate(task);
    }

    @Override
    public Task findById(Long id) {
        return getSession().get(Task.class, id);
    }
}
