package com.example.miha.criminalintent.data.repositories.repositoryOfComments;

import com.example.miha.criminalintent.data.network.comments.ICommentsApi;
import com.example.miha.criminalintent.domain.mapper.CommentsMapper;
import com.example.miha.criminalintent.domain.model.Comment;
import com.example.miha.criminalintent.domain.model.CommentOnServer;
import com.example.miha.criminalintent.domain.model.Crime;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

public class RepositoryOfComments implements IRepositoryOfComments {
    private ICommentsApi commentsApi;

    public RepositoryOfComments(ICommentsApi commentsApi) {
        this.commentsApi = commentsApi;
    }

    @Override
    public Observable<List<Comment>> getComments(Crime crime) {
        String serverIdComments = crime.getAuthor().getServerId() + crime.getDate();
        return commentsApi.getComments(serverIdComments)
                .flatMap((Func1<List<CommentOnServer>, Observable<CommentOnServer>>) Observable::from)
                .map(new CommentsMapper())
                .toList();
    }

    @Override
    public Observable<Boolean> addComment(CommentOnServer crime) {
        return commentsApi.sendComment(crime);
    }
}
