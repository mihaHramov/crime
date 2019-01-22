package com.example.miha.criminalintent.domain.model;

import java.io.Serializable;
import java.util.List;

public class Crime implements Serializable {
    private Integer id;

    private List<Comment> comments;
    private User suspect;
    private String photo;
    private String title;
    private String date;

    private Boolean solved;

    private User author;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public User getSuspect() {
        return suspect;
    }

    public void setSuspect(User mSuspect) {
        this.suspect = mSuspect;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String mPhoto) {
        this.photo = mPhoto;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String mTitle) {
        this.title = mTitle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String mDate) {
        this.date = mDate;
    }

    public Boolean getSolved() {
        return solved;
    }

    public void setSolved(Boolean mSolved) {
        this.solved = mSolved;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
