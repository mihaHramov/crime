package com.example.miha.criminalintent.presentation.mvp.commentsCreateFragment;

import com.arellomobile.mvp.MvpView;
import com.example.miha.criminalintent.domain.model.User;

public interface CommentsCreateFragmentView extends MvpView {
    void showError(String message);

    void showSuccess();

    void setEnabled(Boolean flag);

    void showUserInfo(User user);
}
