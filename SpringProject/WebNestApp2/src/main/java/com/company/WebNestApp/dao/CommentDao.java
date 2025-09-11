package com.company.WebNestApp.dao;

import java.util.List;

import com.company.WebNestApp.model.Comment;

public interface CommentDao {
	void saveComment(Comment comment);
	 List<Comment> findByTaskId(Long taskId);
}
