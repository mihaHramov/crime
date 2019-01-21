package com.example.miha.criminalintent.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Crime {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("date")
    @Expose
    private List<Comment> comments;
    @SerializedName("suspect")
    @Expose
    private User suspect;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("solved")
    @Expose
    private Boolean solved;

    @SerializedName("author")
    @Expose
    private User author;

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
