package com.example.miha.criminalintent.di.auth;

import com.example.miha.criminalintent.data.network.user.UserApi;
import com.example.miha.criminalintent.data.repositories.repositoryOfUser.IRepositoryOfUser;
import com.example.miha.criminalintent.domain.authUserFragment.AuthUserInteractor;
import com.example.miha.criminalintent.domain.authUserFragment.IAuthUserInteractor;
import com.example.miha.criminalintent.presentation.mvp.authUserFragment.AuthUserFragmentPresenter;
import com.example.miha.criminalintent.presentation.ui.utils.ISchedulersProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class AuthModule {

    @Provides
    public IAuthUserInteractor getInteractor(IRepositoryOfUser repositoryOfUser,
                                             UserApi auth,
                                             ISchedulersProvider provider) {
        return new AuthUserInteractor(repositoryOfUser, auth, provider);
    }

    @Provides
    public AuthUserFragmentPresenter providePresenter(IAuthUserInteractor interactor) {
        return new AuthUserFragmentPresenter(interactor);
    }
}
