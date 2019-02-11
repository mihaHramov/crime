package com.example.miha.criminalintent.domain.userListActivity;

import com.example.miha.criminalintent.data.repositories.repositoryOfUser.IRepositoryOfUser;
import com.example.miha.criminalintent.presentation.ui.utils.ISchedulersProvider;


public class UserListActivityInteractor implements IUserListActivityInteractor {
    private IRepositoryOfUser repositoryOfUser;
    private ISchedulersProvider provider;

    public UserListActivityInteractor(IRepositoryOfUser repositoryOfUser, ISchedulersProvider schedulersProvider) {
        this.repositoryOfUser = repositoryOfUser;
        this.provider = schedulersProvider;
    }

    @Override
    public void loadUsers(OnLoadingListener onLoadingListener) {
        repositoryOfUser.getUsers()
                .subscribeOn(provider.newThread())
                .observeOn(provider.ui())
                .subscribe(onLoadingListener::success,throwable -> onLoadingListener.onError(throwable.getMessage()));
    }
}
