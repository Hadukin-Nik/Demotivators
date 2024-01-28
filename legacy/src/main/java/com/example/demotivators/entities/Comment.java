package com.example.demotivators.entities;

import java.util.Date;

public class Comment {

    private int commentId;

    private int memId;
    private int userId;

    private Date date;

    private String text;

    private int likes;
    private int dislikes;

    public Comment(int memId, int userId, Date date, String text, int likes, int dislikes) {
        this.memId = memId;
        this.userId = userId;
        this.date = date;
        this.text = text;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public Comment(int commentId, int memId, int userId, Date date, String text, int likes, int dislikes) {
        this.commentId = commentId;
        this.memId = memId;
        this.userId = userId;
        this.date = date;
        this.text = text;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public int getCommentId() {
        return commentId;
    }

    public int getMemId() {
        return memId;
    }

    public int getUserId() {
        return userId;
    }

    public Date getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }
}
