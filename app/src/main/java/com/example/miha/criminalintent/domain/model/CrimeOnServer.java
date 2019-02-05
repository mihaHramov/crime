package com.example.miha.criminalintent.domain.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class CrimeOnServer {

    public String photo;
    public String title;
    public String date;
    public String details;
    public Boolean solved;

    public CrimeOnServer() {
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("photo", photo);
        result.put("title", title);
        result.put("date", date);
        result.put("details", details);
        result.put("solved", solved);
        return result;
    }
}
