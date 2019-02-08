package com.example.miha.criminalintent.domain.commentsCreateFragment;

public interface ICommentsCreateInteractor {
    void sendMessage(String message, String date, OnSuccess success, OnError error);

    interface OnSuccess {
        void success();
    }

    interface OnError {
        void error(Throwable throwable);
    }
}
