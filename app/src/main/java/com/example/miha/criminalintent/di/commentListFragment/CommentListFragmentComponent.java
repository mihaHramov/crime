package com.example.miha.criminalintent.di.commentListFragment;

import com.example.miha.criminalintent.presentation.mvp.commentsListFragment.CommentsListFragmentPresenter;

import dagger.Subcomponent;

@Subcomponent(modules = CommentsListFragmentModule.class)
public interface CommentListFragmentComponent {
    CommentsListFragmentPresenter getPresenter();
}
