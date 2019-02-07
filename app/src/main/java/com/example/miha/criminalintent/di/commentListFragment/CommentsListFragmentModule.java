package com.example.miha.criminalintent.di.commentListFragment;

import com.example.miha.criminalintent.data.repositories.repositoryOfComments.IRepositoryOfComments;
import com.example.miha.criminalintent.domain.commentsListFragment.CommentsListFragmentInteractor;
import com.example.miha.criminalintent.domain.commentsListFragment.ICommentsListFragmentInteractor;
import com.example.miha.criminalintent.domain.model.Crime;
import com.example.miha.criminalintent.presentation.mvp.commentsListFragment.CommentsListFragmentPresenter;
import com.example.miha.criminalintent.presentation.ui.utils.ISchedulersProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class CommentsListFragmentModule {
    private Crime crime;

    public CommentsListFragmentModule(Crime crime) {
        this.crime = crime;
    }

    @Provides
    ICommentsListFragmentInteractor provideICommentsListFragmentInteractor(IRepositoryOfComments repositoryOfComments, ISchedulersProvider provider) {
        return new CommentsListFragmentInteractor(repositoryOfComments, provider, crime);
    }

    @Provides
    CommentsListFragmentPresenter providePresenter(ICommentsListFragmentInteractor interactor) {
        return new CommentsListFragmentPresenter(interactor);
    }
}
