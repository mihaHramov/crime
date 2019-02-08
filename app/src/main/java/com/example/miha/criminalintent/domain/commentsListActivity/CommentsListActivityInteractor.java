package com.example.miha.criminalintent.domain.commentsListActivity;

import com.example.miha.criminalintent.data.repositories.repositoryOfUser.IRepositoryOfUser;
import com.example.miha.criminalintent.domain.model.Crime;
import com.example.miha.criminalintent.domain.model.User;
import com.example.miha.criminalintent.presentation.ui.utils.ISchedulersProvider;

import rx.Observable;
import rx.functions.Func1;


public class CommentsListActivityInteractor implements ICommentsListActivityInteractor {
    private Crime crime;
    private IRepositoryOfUser repositoryOfUser;
    private ISchedulersProvider schedulers;

    public CommentsListActivityInteractor(Crime crime, IRepositoryOfUser repositoryOfUser, ISchedulersProvider schedulers) {
        this.crime = crime;
        this.repositoryOfUser = repositoryOfUser;
        this.schedulers = schedulers;
    }

    @Override
    public Crime getCrime() {
        return crime;
    }

    @Override
    public void isCanAddComment(OnSuccess successCallback,OnError errorCallback) {
        repositoryOfUser
                .getCurrentUser()
                .flatMap((Func1<User, Observable<User>>) user -> user.getId() > 0 ? Observable.just(user) :Observable.error(new Throwable("not auth user")))
                .subscribeOn(schedulers.newThread())
                .observeOn(schedulers.ui())
                .subscribe(user -> successCallback.success(), errorCallback::error);
    }
}
