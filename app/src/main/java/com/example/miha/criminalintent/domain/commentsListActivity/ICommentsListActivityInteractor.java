package com.example.miha.criminalintent.domain.commentsListActivity;

import com.example.miha.criminalintent.domain.model.Crime;

public interface ICommentsListActivityInteractor {
    Crime getCrime();

    void isCanAddComment(OnSuccess success,OnError error);

    interface OnSuccess {
        void success();
    }
    interface OnError{
        void error(Throwable throwable);
    }
}
