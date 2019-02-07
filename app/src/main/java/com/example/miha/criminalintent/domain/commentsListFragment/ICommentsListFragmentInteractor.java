package com.example.miha.criminalintent.domain.commentsListFragment;

import com.example.miha.criminalintent.domain.model.Comment;

import java.util.List;

public interface ICommentsListFragmentInteractor {
    void getComments(OnSuccessLoadingComments successLoadingComments, OnErrorLoadingComments errorLoadingComments);

    interface OnSuccessLoadingComments {
        void call(List<Comment> comments);
    }

    interface OnErrorLoadingComments {
        void call(Throwable throwable);
    }
}
