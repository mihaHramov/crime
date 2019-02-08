package com.example.miha.criminalintent.di.commentsCreateFragment;

import com.example.miha.criminalintent.presentation.mvp.commentsCreateFragment.CommentsCreateFragmentPresenter;

import dagger.Subcomponent;

@Subcomponent(modules = CommentsCreateFragmentModule.class)
public interface CommentsCreateFragmentComponent {
    CommentsCreateFragmentPresenter getPresenter();
}
