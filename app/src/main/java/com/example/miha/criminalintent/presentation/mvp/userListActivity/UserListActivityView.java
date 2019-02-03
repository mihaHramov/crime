package com.example.miha.criminalintent.presentation.mvp.userListActivity;

import com.arellomobile.mvp.MvpView;
import com.example.miha.criminalintent.domain.model.User;

import java.util.List;

public interface UserListActivityView extends MvpView {
    void showUsers(List<User> users);
    void showError(String error);
    void showLoading(Boolean isShow);
}
