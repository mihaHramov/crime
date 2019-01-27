package com.example.miha.criminalintent.domain.authUserFragment;


import com.example.miha.criminalintent.data.network.user.AuthUserApi;
import com.example.miha.criminalintent.data.repositories.repositoryOfUser.IRepositoryOfUser;
import com.example.miha.criminalintent.domain.model.User;
import com.example.miha.criminalintent.presentation.ui.utils.ISchedulersProvider;

import rx.Observable;
import rx.functions.Func1;


public class AuthUserInteractor implements IAuthUserInteractor {
    private IRepositoryOfUser mRepositoryOfUser;
    private AuthUserApi mAuth;
    private ISchedulersProvider provider;

    public AuthUserInteractor(IRepositoryOfUser mRepositoryOfUser, AuthUserApi mAuth, ISchedulersProvider provider) {
        this.mRepositoryOfUser = mRepositoryOfUser;
        this.mAuth = mAuth;
        this.provider = provider;
    }

    @Override
    public void authUser(String login, String password, Callback callback) {
        this.getUser(mAuth.login(login, password), callback);
    }

    @Override
    public void registerUser(String login, String password, Callback callback) {
        this.getUser(mAuth.registration(login, password), callback);
    }

    private void getUser(Observable<User> userObservable, Callback callback) {
        userObservable.flatMap(user -> mRepositoryOfUser.addUserIfNotExist(user))//add to db if not exist
                .doOnNext(user -> mRepositoryOfUser.setCurrentUser(user))
                .flatMap((Func1<User, Observable<User>>) user -> mRepositoryOfUser.createUser(user))//add user to server storage
                .subscribeOn(provider.newThread())
                .observeOn(provider.ui())
                .subscribe(callback::onSuccess, throwable -> callback.onError(throwable.getMessage()));
    }
}
