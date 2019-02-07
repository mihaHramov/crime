package com.example.miha.criminalintent.presentation.mvp.commentsListFragment;

import com.arellomobile.mvp.MvpView;
import com.example.miha.criminalintent.domain.model.Comment;

import java.util.List;

public interface CommentsListFragmentView extends MvpView {
    void showComments(List<Comment> comments);
}
