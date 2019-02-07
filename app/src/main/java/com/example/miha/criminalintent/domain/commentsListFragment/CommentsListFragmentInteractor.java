package com.example.miha.criminalintent.domain.commentsListFragment;

import com.example.miha.criminalintent.data.repositories.repositoryOfComments.IRepositoryOfComments;
import com.example.miha.criminalintent.domain.model.Crime;
import com.example.miha.criminalintent.presentation.ui.utils.ISchedulersProvider;

public class CommentsListFragmentInteractor implements ICommentsListFragmentInteractor {

    private IRepositoryOfComments repositoryOfComments;
    private ISchedulersProvider schedulersProvider;
    private Crime crime;

    public CommentsListFragmentInteractor(IRepositoryOfComments repositoryOfComments, ISchedulersProvider schedulersProvider, Crime crime) {
        this.repositoryOfComments = repositoryOfComments;
        this.schedulersProvider = schedulersProvider;
        this.crime = crime;
    }

    @Override
    public void getComments(OnSuccessLoadingComments successLoadingComments, OnErrorLoadingComments errorLoadingComments) {
        repositoryOfComments
                .getComments(crime)
                .subscribeOn(schedulersProvider.io())
                .observeOn(schedulersProvider.ui())
                .doOnNext(successLoadingComments::call)
                .doOnError(errorLoadingComments::call)
                .subscribe();
    }
}
