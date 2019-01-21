package com.example.miha.criminalintent.domain.mapper;

import com.example.miha.criminalintent.domain.model.User;
import com.google.firebase.auth.FirebaseUser;

import rx.functions.Func1;

public class FirebaseUserMaper implements Func1<FirebaseUser, User> {
    @Override
    public User call(FirebaseUser firebaseUser) {
        User user = new User();
        user.setName(firebaseUser.getEmail());
        return user;
    }
}
