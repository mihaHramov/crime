package com.example.miha.criminalintent.domain.model;

import java.io.Serializable;

public class Comment implements Serializable {
    private String mDate;
    private String mMessage;
    private User mAuthor;
    private Integer mId;

    public Integer getId() {
        return mId;
    }

    public void setId(Integer mId) {
        this.mId = mId;
    }

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
