package com.company.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.company.model.Comment;
import com.company.model.Task;
import com.company.model.User;
import com.company.repository.CommentRepository;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    // Constructor Injection
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    
    // Add a new comment
    public Comment addComment(Task task, User user, String content) {
        Comment comment = new Comment(task, user, content);
        return commentRepository.save(comment);
    }

    // Create or update a comment
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    // Find comment by ID
    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }

    // Get all comments
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    // Get comments by task (sorted ascending by created time)
    public List<Comment> getCommentsByTask(Task task) {
        return commentRepository.findByTaskOrderByCreatedAtAsc(task);
    }

    // Get comments by user
    public List<Comment> getCommentsByUser(User user) {
        return commentRepository.findByUser(user);
    }

    // Get comments by task and user
    public List<Comment> getCommentsByTaskAndUser(Task task, User user) {
        return commentRepository.findByTaskAndUser(task, user);
    }

    // Count comments for a task
    public long countCommentsByTask(Task task) {
        return commentRepository.countByTask(task);
    }

    // Get all comments sorted by newest first
    public List<Comment> getAllCommentsNewestFirst() {
        return commentRepository.findAllByOrderByCreatedAtDesc();
    }

    // Get top 10 newest comments
    public List<Comment> getTop10NewestComments() {
        return commentRepository.findTop10ByOrderByCreatedAtDesc();
    }

    // Delete comment by ID
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
