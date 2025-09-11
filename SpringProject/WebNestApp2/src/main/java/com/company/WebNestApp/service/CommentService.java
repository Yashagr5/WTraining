package com.company.WebNestApp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.company.WebNestApp.dao.CommentDao;
import com.company.WebNestApp.model.Comment;

@Service
public class CommentService {
private final CommentDao dao;

public CommentService(CommentDao dao) {
	this.dao = dao;
}
public void saveComment(Comment comment) {
    dao.saveComment(comment);
}
public List<Comment> findByTaskId(Long taskId) {
    return dao.findByTaskId(taskId);
}
public List<Comment> listAll() {
    return dao.findAll();
}
}
