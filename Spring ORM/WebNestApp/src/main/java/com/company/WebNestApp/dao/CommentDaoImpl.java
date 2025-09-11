package com.company.WebNestApp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.company.WebNestApp.model.Comment;

@Repository
public class CommentDaoImpl implements CommentDao{
	@Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    

    @Override
    public void saveComment(Comment comment) {
        getSession().saveOrUpdate(comment);
    }
    
    @Override
    public List<Comment> findByTaskId(Long taskId) {
        return getSession()
                .createQuery("from Comment where task.id = :taskId", Comment.class)
                .setParameter("taskId", taskId)
                .list();
    }
}
