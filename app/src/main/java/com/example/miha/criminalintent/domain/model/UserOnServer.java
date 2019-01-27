package com.example.miha.criminalintent.domain.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class UserOnServer {

    public String name;
    public String photo;


    public UserOnServer() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public UserOnServer(String name, String photo) {
        this.name = name;
        this.photo = photo;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("photo", photo);
        return result;
    }
}
