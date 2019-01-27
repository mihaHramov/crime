package com.example.miha.criminalintent.presentation.mvp.userListActivity;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.miha.criminalintent.domain.model.User;
import com.example.miha.criminalintent.domain.userListActivity.IUserListActivityInteractor;

import java.util.List;

@InjectViewState
public class UserListActivityPresenter extends MvpPresenter<UserListActivityView> {
    private IUserListActivityInteractor interactor;

    public UserListActivityPresenter(IUserListActivityInteractor interactor) {
        this.interactor = interactor;
    }

    public void init() {
        interactor.loadUsers(new IUserListActivityInteractor.OnLoadingListener() {
            @Override
            public void success(List<User> users) {
                getViewState().showUsers(users);
            }

            @Override
            public void onError(String message) {
                getViewState().showError(message);
            }
        });
    }
}
