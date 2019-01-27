package com.example.miha.criminalintent.presentation.mvp.authUserFragment;

import com.arellomobile.mvp.MvpView;
import com.example.miha.criminalintent.domain.model.User;

public interface IAuthUserFragmentView extends MvpView {
    void showUser(User user);

    void showError(String error);

    void disableView(Boolean b);
}
