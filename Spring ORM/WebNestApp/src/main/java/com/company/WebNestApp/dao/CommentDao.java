package com.company.WebNestApp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.company.WebNestApp.model.Comment;

@Repository
public interface CommentDao {
	void saveComment(Comment comment);
	 List<Comment> findByTaskId(Long taskId);
}
