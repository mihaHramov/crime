package com.example.miha.criminalintent.presentation.mvp.commentsListActivity;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.miha.criminalintent.domain.model.Crime;

@InjectViewState
public class CommentListActivityPresenter extends MvpPresenter<CommentsListActivityView> {
    private Crime crime;

    public CommentListActivityPresenter(Crime crime) {
        this.crime = crime;
    }

    public void init() {
       getViewState().showComments(crime);
    }
}
