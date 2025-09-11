package com.company.dto;

import java.util.List;

import com.company.model.Comment;
import com.company.model.Task;
import com.company.model.User;

public class DTOConverter {

    public static UserDTO toUserDTO(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getRole().name());
    }

    public static CommentDTO toCommentDTO(Comment comment) {
        return new CommentDTO(
            comment.getId(),
            comment.getCommentText(),
            comment.getCreatedAt(),
            toUserDTO(comment.getUser())
        );
    }

    public static TaskDTO toTaskDTO(Task task) {
        List<UserDTO> users = task.getAssignedUsers()
                                  .stream()
                                  .map(DTOConverter::toUserDTO)
                                  .toList();

        List<CommentDTO> comments = task.getComments()
                                        .stream()
                                        .map(DTOConverter::toCommentDTO)
                                        .toList();

        return new TaskDTO(
            task.getId(),
            task.getTitle(),
            task.getDescription(),
            task.getStatus().name(),
            task.getStartDate(),
            task.getDueDate(),
            users,
            comments
        );
    }

    public static List<TaskDTO> toTaskDTOList(List<Task> tasks) {
        return tasks.stream().map(DTOConverter::toTaskDTO).toList();
    }
}
