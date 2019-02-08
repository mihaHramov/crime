package com.example.miha.criminalintent.di.commentsCreateFragment;

import com.example.miha.criminalintent.data.repositories.repositoryOfComments.IRepositoryOfComments;
import com.example.miha.criminalintent.data.repositories.repositoryOfUser.IRepositoryOfUser;
import com.example.miha.criminalintent.domain.commentsCreateFragment.CommentsCreateInteractor;
import com.example.miha.criminalintent.domain.commentsCreateFragment.ICommentsCreateInteractor;
import com.example.miha.criminalintent.domain.model.Crime;
import com.example.miha.criminalintent.presentation.mvp.commentsCreateFragment.CommentsCreateFragmentPresenter;
import com.example.miha.criminalintent.presentation.ui.utils.ISchedulersProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class CommentsCreateFragmentModule {
    private Crime crime;

    public CommentsCreateFragmentModule(Crime crime) {
        this.crime = crime;
    }

    @Provides
    ICommentsCreateInteractor provideCommentsCreateInteractor(IRepositoryOfUser repositoryOfUser, IRepositoryOfComments repositoryOfComments, ISchedulersProvider provider) {
        return new CommentsCreateInteractor(repositoryOfUser, crime, repositoryOfComments, provider);
    }

    @Provides
    CommentsCreateFragmentPresenter provideCommentsCreateFragmentPresenter(ICommentsCreateInteractor interactor) {
        return new CommentsCreateFragmentPresenter(interactor);
    }
}
