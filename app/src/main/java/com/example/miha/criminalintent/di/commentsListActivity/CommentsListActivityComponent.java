package com.example.miha.criminalintent.di.commentsListActivity;

import com.example.miha.criminalintent.presentation.mvp.commentsListActivity.CommentListActivityPresenter;

import dagger.Subcomponent;

@Subcomponent(modules = CommentsListActivityModule.class)
public interface CommentsListActivityComponent {
    CommentListActivityPresenter getPresenter();
}
