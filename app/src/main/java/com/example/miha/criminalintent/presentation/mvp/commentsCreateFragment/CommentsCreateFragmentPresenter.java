package com.example.miha.criminalintent.presentation.mvp.commentsCreateFragment;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.miha.criminalintent.domain.commentsCreateFragment.ICommentsCreateInteractor;

@InjectViewState
public class CommentsCreateFragmentPresenter extends MvpPresenter<CommentsCreateFragmentView> {
    private ICommentsCreateInteractor interactor;

    public CommentsCreateFragmentPresenter(ICommentsCreateInteractor interactor) {
        this.interactor = interactor;
    }

    public void init() {
        interactor.getUser(user -> {
            if (!user.getPhoto().isEmpty()) {
                getViewState().showUserPhoto(user.getPhoto());
            }
            getViewState().showUserName(user.getName());
        });
    }

    public void sendComment(String message, String date) {
        getViewState().setEnabled(true);
        interactor.sendMessage(message, date, () -> {
            getViewState().showSuccess();
            getViewState().setEnabled(false);
        }, throwable -> {
            getViewState().showError(throwable.getMessage());
            getViewState().setEnabled(false);
        });
    }

}
