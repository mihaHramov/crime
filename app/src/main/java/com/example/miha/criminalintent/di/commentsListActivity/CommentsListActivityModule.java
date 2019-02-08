package com.example.miha.criminalintent.di.commentsListActivity;

import com.example.miha.criminalintent.data.repositories.repositoryOfUser.IRepositoryOfUser;
import com.example.miha.criminalintent.domain.commentsListActivity.CommentsListActivityInteractor;
import com.example.miha.criminalintent.domain.commentsListActivity.ICommentsListActivityInteractor;
import com.example.miha.criminalintent.domain.model.Crime;
import com.example.miha.criminalintent.presentation.mvp.commentsListActivity.CommentListActivityPresenter;
import com.example.miha.criminalintent.presentation.ui.utils.ISchedulersProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class CommentsListActivityModule {
    private Crime crime;

    public CommentsListActivityModule(Crime crime) {
        this.crime = crime;
    }

    @Provides
    CommentListActivityPresenter providePresenter(ICommentsListActivityInteractor interactor) {
        return new CommentListActivityPresenter(interactor);
    }

    @Provides
    ICommentsListActivityInteractor provideInteractor(IRepositoryOfUser repositoryOfUser, ISchedulersProvider provider) {
        return new CommentsListActivityInteractor(crime, repositoryOfUser, provider);
    }
}
