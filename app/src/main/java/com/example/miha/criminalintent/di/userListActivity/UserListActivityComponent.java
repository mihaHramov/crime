package com.example.miha.criminalintent.di.userListActivity;

import com.example.miha.criminalintent.presentation.mvp.userListActivity.UserListActivityPresenter;
import com.example.miha.criminalintent.presentation.ui.fragment.UserListFragment;

import dagger.Subcomponent;

@Subcomponent(modules = UserListActivityModule.class)
public interface UserListActivityComponent {
    UserListActivityPresenter provideUserListActivityPresenter();

    void inject(UserListFragment userListFragment);
}
