package com.example.miha.criminalintent.data.network.user;

import com.example.miha.criminalintent.domain.model.User;

import rx.Observable;

public interface AuthUserApi {
    Observable<User> login(String login,String password);
    Observable<User> registration(String login,String password);
}
