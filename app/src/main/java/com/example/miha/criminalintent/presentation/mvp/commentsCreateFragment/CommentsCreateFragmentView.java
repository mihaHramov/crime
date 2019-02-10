package com.example.miha.criminalintent.presentation.mvp.commentsCreateFragment;

import com.arellomobile.mvp.MvpView;

public interface CommentsCreateFragmentView extends MvpView {
    void showError(String message);

    void showSuccess();

    void setEnabled(Boolean flag);

    void showUserPhoto(String photo);

    void showUserName(String name);
}
