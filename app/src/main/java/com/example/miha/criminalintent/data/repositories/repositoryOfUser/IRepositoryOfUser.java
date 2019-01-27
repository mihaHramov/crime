package com.example.miha.criminalintent.data.repositories.repositoryOfUser;

import com.example.miha.criminalintent.domain.model.User;

import java.util.List;

import rx.Observable;

public interface IRepositoryOfUser {
    Observable<User> createUser(User user);
    Observable<List<User>> getUsers();
    Observable<User> addUserIfNotExist(User user);
    Observable<User> getCurrentUser();
    void setCurrentUser(User user);
}
