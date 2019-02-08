package com.example.miha.criminalintent.domain.commentsCreateFragment;

import com.example.miha.criminalintent.domain.model.User;

public interface ICommentsCreateInteractor {
    void getUser(OnCompleteLoadUser loadUser);

    interface OnCompleteLoadUser{
        void success(User user);
    }
    void sendMessage(String message, String date, OnSuccess success, OnError error);

    interface OnSuccess {
        void success();
    }

    interface OnError {
        void error(Throwable throwable);
    }
}
