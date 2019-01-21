package com.example.miha.criminalintent.domain.authUserFragment;


import com.example.miha.criminalintent.data.network.user.UserApi;
import com.example.miha.criminalintent.data.repositories.repositoryOfUser.IRepositoryOfUser;
import com.example.miha.criminalintent.presentation.ui.utils.ISchedulersProvider;
import com.google.firebase.auth.FirebaseAuth;


public class AuthUserInteractor implements IAuthUserInteractor {
    private IRepositoryOfUser mRepositoryOfUser;
    private UserApi mAuth;
    private ISchedulersProvider provider;

    public AuthUserInteractor(IRepositoryOfUser mRepositoryOfUser, UserApi mAuth, ISchedulersProvider provider) {
        this.mRepositoryOfUser = mRepositoryOfUser;
        this.mAuth = mAuth;
        this.provider = provider;
    }

    @Override
    public void authUser(String login, String password, Callback callback) {
        mAuth.login(login, password)
                .subscribeOn(provider.newThread())
                .observeOn(provider.ui())
                .subscribe(callback::onSuccess, throwable -> callback.onError(throwable.getMessage()));
    }

    @Override
    public void registerUser(String login, String password, Callback callback) {
                mAuth.registration(login, password)
                        .subscribeOn(provider.newThread())
                        .observeOn(provider.ui())
                        .subscribe(callback::onSuccess, throwable -> callback.onError(throwable.getMessage()));

    }
}
