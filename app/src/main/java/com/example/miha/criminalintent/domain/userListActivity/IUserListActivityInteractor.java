package com.example.miha.criminalintent.domain.userListActivity;

import com.example.miha.criminalintent.domain.model.User;

import java.util.List;

public interface IUserListActivityInteractor {
    void loadUsers(OnLoadingListener onLoadingListener);

    interface OnLoadingListener {
        void success(List<User> users);

        void onError(String message);
    }
}
