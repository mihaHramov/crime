package com.example.miha.criminalintent.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comment {
    @SerializedName("date")
    @Expose
    private String mDate;

    @SerializedName("message")
    @Expose
    private String mMessage;

    @SerializedName("author")
    @Expose
    private User mAuthor;

    public String getDate() {
        return mDate;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    public User getAuthor() {
        return mAuthor;
    }

    public void setAuthor(User mAuthor) {
        this.mAuthor = mAuthor;
    }
}
