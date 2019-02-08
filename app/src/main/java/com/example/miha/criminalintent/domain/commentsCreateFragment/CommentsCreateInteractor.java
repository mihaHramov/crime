package com.example.miha.criminalintent.domain.commentsCreateFragment;

import com.example.miha.criminalintent.data.repositories.repositoryOfComments.IRepositoryOfComments;
import com.example.miha.criminalintent.data.repositories.repositoryOfUser.IRepositoryOfUser;
import com.example.miha.criminalintent.domain.model.CommentOnServer;
import com.example.miha.criminalintent.domain.model.Crime;
import com.example.miha.criminalintent.domain.model.User;
import com.example.miha.criminalintent.presentation.ui.utils.ISchedulersProvider;

import rx.Observable;
import rx.functions.Func1;

public class CommentsCreateInteractor implements ICommentsCreateInteractor {
    private IRepositoryOfUser repositoryOfUser;
    private Crime crime;
    private IRepositoryOfComments repositoryOfComments;
    private ISchedulersProvider schedulers;

    public CommentsCreateInteractor(IRepositoryOfUser repositoryOfUser, Crime crime, IRepositoryOfComments repositoryOfComments, ISchedulersProvider schedulers) {
        this.repositoryOfUser = repositoryOfUser;
        this.crime = crime;
        this.repositoryOfComments = repositoryOfComments;
        this.schedulers = schedulers;
    }

    @Override
    public void sendMessage(String message, String data, OnSuccess success, OnError error) {
        Observable<Crime> crimeObservable = Observable.just(crime);
        Observable<String> dataObservable = Observable.just(data);
        Observable<String> messageObservable = Observable.just(message);
        Observable<User> userObservable = repositoryOfUser.getCurrentUser().flatMap((Func1<User, Observable<User>>) user -> user.getId() > 0 ? Observable.just(user) : Observable.error(new Throwable("please auth")));

        Observable.zip(
                crimeObservable, messageObservable, dataObservable, userObservable,
                (crime, message1, date, user) -> {
                    CommentOnServer commentOnServer = new CommentOnServer();
                    commentOnServer.authorId = user.getServerId();
                    commentOnServer.authorName = user.getName();
                    commentOnServer.authorPhoto = user.getPhoto();
                    commentOnServer.message = message1;
                    commentOnServer.date = date;
                    return commentOnServer;
                }
        )
                .flatMap((Func1<CommentOnServer, Observable<Boolean>>) commentOnServer -> repositoryOfComments.addComment(commentOnServer))
                .subscribeOn(schedulers.newThread())
                .observeOn(schedulers.ui())
                .subscribe(aBoolean -> success.success(), error::error);
    }

    @Override
    public void getUser(OnCompleteLoadUser loadUser) {
        repositoryOfUser.getCurrentUser()
                .subscribeOn(schedulers.newThread())
                .observeOn(schedulers.ui())
                .subscribe(loadUser::success);
    }
}
