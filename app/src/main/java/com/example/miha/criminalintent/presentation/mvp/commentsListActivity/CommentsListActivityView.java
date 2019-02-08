package com.example.miha.criminalintent.presentation.mvp.commentsListActivity;

import com.arellomobile.mvp.MvpView;
import com.example.miha.criminalintent.domain.model.Crime;

public interface CommentsListActivityView extends MvpView {
    void showComments(Crime crime);

    void showError(String message);

    void showFormComments();
}
