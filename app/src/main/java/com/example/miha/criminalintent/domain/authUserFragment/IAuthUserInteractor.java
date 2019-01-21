package com.example.miha.criminalintent.domain.authUserFragment;

import com.example.miha.criminalintent.domain.model.User;

public interface IAuthUserInteractor {
    void authUser(String login,String password,Callback callback);
    void registerUser(String login,String password,Callback callback);


    interface Callback{
        void onSuccess(User user);
        void onError(String ex);
    }
}
