package com.example.miha.criminalintent.domain.model;

import java.io.Serializable;

public class User implements Serializable {

    private String mName;
    private String mPhoto;
    private Integer mId;
    private String mServerId;

    public String getServerId() {
        return mServerId;
    }

    public void setServerId(String serverId) {
        this.mServerId = serverId;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        this.mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getPhoto() {
        return mPhoto;
    }

    public void setPhoto(String mPhoto) {
        this.mPhoto = mPhoto;
    }


}
