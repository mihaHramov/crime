package com.example.miha.criminalintent.data.repositories.repositoryOfUser;

import com.example.miha.criminalintent.domain.model.User;

import rx.Observable;

public interface IRepositoryOfUser {
    Observable<User> getCurrentUser();
    void setCurrentUser(User user);
}
