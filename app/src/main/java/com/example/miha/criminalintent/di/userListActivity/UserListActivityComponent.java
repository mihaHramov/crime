package com.example.miha.criminalintent.di.userListActivity;

import com.example.miha.criminalintent.presentation.mvp.userListActivity.UserListActivityPresenter;

import dagger.Subcomponent;

@Subcomponent(modules = UserListActivityModule.class)
public interface UserListActivityComponent {
    UserListActivityPresenter provideUserListActivityPresenter();
}
