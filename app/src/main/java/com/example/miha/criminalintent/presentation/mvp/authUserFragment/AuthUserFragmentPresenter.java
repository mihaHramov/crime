package com.example.miha.criminalintent.presentation.mvp.authUserFragment;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.miha.criminalintent.domain.authUserFragment.IAuthUserInteractor;
import com.example.miha.criminalintent.domain.model.User;

@InjectViewState
public class AuthUserFragmentPresenter extends MvpPresenter<IAuthUserFragmentView> {
    private IAuthUserInteractor interactor;
    private IAuthUserInteractor.Callback callback = new IAuthUserInteractor.Callback() {
        @Override
        public void onSuccess(User user) {
            getViewState().showUser(user);
        }

        @Override
        public void onError(String ex) {
            getViewState().showError(ex);
        }
    };


    public AuthUserFragmentPresenter(IAuthUserInteractor interactor) {
        this.interactor = interactor;
    }

    public void auth(String email, String password, Boolean register){
        if(register){
            interactor.registerUser(email, password,callback);
        }else {
            interactor.authUser(email,password,callback);
        }
    }
}
