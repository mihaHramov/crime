package com.example.miha.criminalintent.domain.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class CrimeOnServer {

    public String photo;
    public String author_name;
    public String author_id;
    public String suspect_name;
    public String suspect_id;
    public String title;
    public String date;
    public String details;
    public Boolean solved;
    public String author_photo;
    public String suspect_photo;

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
        result.put("author_name", author_name);
        result.put("author_id", author_id);
        result.put("suspect_name", suspect_name);
        result.put("suspect_photo", suspect_photo);
        result.put("author_photo", author_photo);
        result.put("suspect_id", suspect_id);
        return result;
    }
}
