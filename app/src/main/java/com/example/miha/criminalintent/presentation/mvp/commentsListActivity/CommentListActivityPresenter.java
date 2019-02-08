package com.example.miha.criminalintent.presentation.mvp.commentsListActivity;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.miha.criminalintent.domain.commentsListActivity.ICommentsListActivityInteractor;
import com.example.miha.criminalintent.domain.model.Crime;

@InjectViewState
public class CommentListActivityPresenter extends MvpPresenter<CommentsListActivityView> {
    private ICommentsListActivityInteractor interactor;

    public CommentListActivityPresenter(ICommentsListActivityInteractor interactor) {
          this.interactor = interactor;
    }

    public void init() {
        getViewState().showComments(interactor.getCrime());
    }

    public void createComments() {
        interactor.isCanAddComment(() -> getViewState().showFormComments(), throwable -> getViewState().showError(throwable.getMessage()));
    }
}