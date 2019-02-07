package com.example.miha.criminalintent.domain.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class CommentOnServer {
    public String authorId;
    public String authorPhoto;
    public String authorName;
    public String date;
    public String message;
    public String crimeId;

    public CommentOnServer() {
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("authorPhoto", authorPhoto);
        result.put("crimeId", crimeId);
        result.put("authorId", authorId);
        result.put("authorName", authorName);
        result.put("date", date);
        result.put("message", message);
        return result;
    }
}
