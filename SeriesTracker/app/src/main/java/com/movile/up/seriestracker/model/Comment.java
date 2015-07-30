package com.movile.up.seriestracker.model;

/**
 * Created by android on 7/30/15.
 */
public class Comment {
    private int id;
    private int parentId;
    private String createdAt;
    private String comment;
    private boolean spoiler;
    private boolean review;
    private int replies;
    private int userRating;
    private User user;

    public int id() {
        return id;
    }

    public int parentId() {
        return parentId;
    }

    public String createdAt() {
        return createdAt;
    }

    public String comment() {
        return comment;
    }

    public boolean spoiler() {
        return spoiler;
    }

    public boolean review() {
        return review;
    }

    public int replies() {
        return replies;
    }

    public int userRating() {
        return userRating;
    }

    public User user() {
        return user;
    }
}
