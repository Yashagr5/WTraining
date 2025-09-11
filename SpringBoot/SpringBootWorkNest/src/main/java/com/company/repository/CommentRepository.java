package com.company.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.model.Comment;
import com.company.model.Task;
import com.company.model.User;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByTaskOrderByCreatedAtAsc(Task task);

    List<Comment> findByUser(User user);

    List<Comment> findByTaskAndUser(Task task, User user);

    long countByTask(Task task);
    
    List<Comment> findAllByOrderByCreatedAtDesc();
    
    List<Comment> findTop10ByOrderByCreatedAtDesc();
}
