package com.example.miha.criminalintent.data.network.user;

import com.example.miha.criminalintent.domain.model.User;

import java.util.List;

import rx.Observable;

public interface UserStorageApi {
    Observable<User> createUser(User user);
    Observable<List<User>> getAllUser();
}
