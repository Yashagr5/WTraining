package com.company.WebNest.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.company.WebNest.model.Comment;

import jakarta.transaction.Transactional;

@Repository
public class CommentDaoImpl implements CommentDao{
	private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    

    @Override
    @Transactional
    public void saveComment(Comment comment) {
        getSession().saveOrUpdate(comment);
    }
    
    @Override
    @Transactional
    public List<Comment> findByTaskId(Long taskId) {
        return getSession()
                .createQuery("from Comment where task.id = :taskId", Comment.class)
                .setParameter("taskId", taskId)
                .list();
    }
    
    @Override
    @Transactional
    public List<Comment> findAll() {
        return getSession()
                .createQuery("FROM Comment", Comment.class)
                .list();
    }
    
    @Override
    @Transactional
    public void deleteCommentById(Long id) {
        Comment comment = getSession().get(Comment.class, id);
        if (comment != null) {
            getSession().delete(comment);
        }
    }
}
