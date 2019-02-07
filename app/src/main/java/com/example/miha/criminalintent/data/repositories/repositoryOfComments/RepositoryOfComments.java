package com.example.miha.criminalintent.data.repositories.repositoryOfComments;

import com.example.miha.criminalintent.data.network.comments.ICommentsApi;
import com.example.miha.criminalintent.domain.model.Comment;
import com.example.miha.criminalintent.domain.model.Crime;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

public class RepositoryOfComments implements IRepositoryOfComments {
    private ICommentsApi commentsApi;

    public RepositoryOfComments(ICommentsApi commentsApi) {
        this.commentsApi = commentsApi;
    }

    @Override
    public Observable<List<Comment>> getComments(Crime crime) {
        List<Comment> list = new ArrayList<>();
        return Observable.just(list);
    }
}
