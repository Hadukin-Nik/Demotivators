package com.example.demotivators.entities.forPages;

import com.example.demotivators.dao_s.UsersDAO;
import com.example.demotivators.entities.Comment;
import com.example.demotivators.entities.User;

import java.util.Date;

public class CommentWithUser extends Comment {
    private User user;
    public CommentWithUser(Comment comment) {
        super(comment.getCommentId(),comment.getMemId(),comment.getUserId(),comment.getDate(),comment.getText(),comment.getLikes(),comment.getDislikes());

        user = UsersDAO.findUser(comment.getUserId());
    }

    public User getUser() {
        return user;
    }
}
