package com.company.WebNest.dao;

import java.util.List;

import com.company.WebNest.model.Comment;

public interface CommentDao {
	void saveComment(Comment comment);
	 List<Comment> findByTaskId(Long taskId);
	 List<Comment> findAll();
	 void deleteCommentById(Long id);
}

