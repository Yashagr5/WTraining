package com.company.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.company.dto.CommentDTO;

@FeignClient(name = "APP-MS")
public interface CommentClient {

    @GetMapping("/api/comments/app/{appId}")
    List<CommentDTO> getCommentsByApp(@PathVariable("appId") Integer appId);
    
    @PostMapping("/api/comments")
    CommentDTO createComment(@RequestBody CommentDTO comment);
    
    @GetMapping("/api/comments/user/{userId}")
    List<CommentDTO> getCommentsByUser(@PathVariable("userId") Integer userId);
}
