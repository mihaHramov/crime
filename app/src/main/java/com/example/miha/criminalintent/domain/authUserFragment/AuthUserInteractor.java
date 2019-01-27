package com.example.miha.criminalintent.domain.authUserFragment;


import com.example.miha.criminalintent.data.network.user.AuthUserApi;
import com.example.miha.criminalintent.data.network.user.UserStorageApi;
import com.example.miha.criminalintent.data.repositories.repositoryOfUser.IRepositoryOfUser;
import com.example.miha.criminalintent.domain.model.User;
import com.example.miha.criminalintent.presentation.ui.utils.ISchedulersProvider;

import rx.Observable;
import rx.functions.Func1;


public class AuthUserInteractor implements IAuthUserInteractor {
    private IRepositoryOfUser mRepositoryOfUser;
    private UserStorageApi mUserStorageApi;
    private AuthUserApi mAuth;
    private ISchedulersProvider provider;

    public AuthUserInteractor(IRepositoryOfUser mRepositoryOfUser, AuthUserApi mAuth, ISchedulersProvider provider, UserStorageApi api) {
        this.mRepositoryOfUser = mRepositoryOfUser;
        this.mAuth = mAuth;
        this.provider = provider;
        this.mUserStorageApi = api;
    }

    @Override
    public void authUser(String login, String password, Callback callback) {
        mAuth.login(login, password)
                .flatMap(user -> mRepositoryOfUser.addUserIfNotExist(user))//add to db if not exist
                .doOnNext(user -> mRepositoryOfUser.setCurrentUser(user))
                .flatMap((Func1<User, Observable<User>>) user -> mUserStorageApi.createUser(user))
                .subscribeOn(provider.newThread())
                .observeOn(provider.ui())
                .subscribe(callback::onSuccess, throwable -> callback.onError(throwable.getMessage()));
    }

    @Override
    public void registerUser(String login, String password, Callback callback) {
        mAuth.registration(login, password)
                .flatMap(user -> mRepositoryOfUser.addUserIfNotExist(user))//add to db if not exist
                .doOnNext(user -> mRepositoryOfUser.setCurrentUser(user))
                .flatMap((Func1<User, Observable<User>>) user -> mUserStorageApi.createUser(user))//add user to server storage
                .subscribeOn(provider.newThread())
                .observeOn(provider.ui())
                .subscribe(callback::onSuccess, throwable -> callback.onError(throwable.getMessage()));

    }
}
