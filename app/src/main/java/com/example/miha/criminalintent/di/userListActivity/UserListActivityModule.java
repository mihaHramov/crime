package com.example.miha.criminalintent.di.userListActivity;

import com.example.miha.criminalintent.data.repositories.repositoryOfUser.IRepositoryOfUser;
import com.example.miha.criminalintent.domain.userListActivity.IUserListActivityInteractor;
import com.example.miha.criminalintent.domain.userListActivity.UserListActivityInteractor;
import com.example.miha.criminalintent.presentation.mvp.userListActivity.UserListActivityPresenter;
import com.example.miha.criminalintent.presentation.ui.utils.ISchedulersProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class UserListActivityModule {
    @Provides
    IUserListActivityInteractor providUserListActivityInteractor(IRepositoryOfUser repositoryOfUser, ISchedulersProvider provider) {
        return new UserListActivityInteractor(repositoryOfUser, provider);
    }
    @Provides
    UserListActivityPresenter providePresenter(IUserListActivityInteractor interactor){
        return new UserListActivityPresenter(interactor);
    }
}
