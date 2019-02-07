package com.example.miha.criminalintent.presentation.mvp.commentsListFragment;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.miha.criminalintent.domain.commentsListFragment.ICommentsListFragmentInteractor;

@InjectViewState
public class CommentsListFragmentPresenter extends MvpPresenter<CommentsListFragmentView> {
    private  ICommentsListFragmentInteractor interactor;

    public CommentsListFragmentPresenter(ICommentsListFragmentInteractor interactor) {
        this.interactor = interactor;
    }

    public void  init(){
        getViewState().showLoading(true);
        interactor.getComments(comments -> {
            getViewState().showComments(comments);
            getViewState().showLoading(false);
        }, throwable -> {
            getViewState().showError(throwable.getMessage());
            getViewState().showLoading(false);
        });
    }
}
