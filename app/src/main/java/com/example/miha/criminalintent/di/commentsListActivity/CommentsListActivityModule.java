package com.example.miha.criminalintent.di.commentsListActivity;

import com.example.miha.criminalintent.domain.model.Crime;
import com.example.miha.criminalintent.presentation.mvp.commentsListActivity.CommentListActivityPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class CommentsListActivityModule {
    private Crime crime;

    public CommentsListActivityModule(Crime crime) {
        this.crime = crime;
    }

    @Provides
    CommentListActivityPresenter providePresenter() {
        return new CommentListActivityPresenter(crime);
    }
}
