package com.company.WebNestApp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.WebNestApp.dao.CommentDao;
import com.company.WebNestApp.model.Comment;

@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
    private CommentDao commentDao;

    @Override
    public Integer add(Comment comment) {
        return commentDao.save(comment);
    }

    @Override
    public void update(Comment comment) {
        commentDao.update(comment);
    }

    @Override
    public void delete(Integer id) {
        commentDao.delete(id);
    }

    @Override
    public Comment get(Integer id) {
        return commentDao.findById(id);
    }

    @Override
    public List<Comment> listAll() {
        return commentDao.findAll();
    }

    @Override
    public List<Comment> listByTask(Integer taskId) {
        return commentDao.findByTask(taskId);
    }

    @Override
    public List<Comment> listByUser(Integer userId) {
        return commentDao.findByUser(userId);
    }
}
