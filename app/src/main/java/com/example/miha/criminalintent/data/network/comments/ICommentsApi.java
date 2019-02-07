package com.example.miha.criminalintent.data.network.comments;

import com.example.miha.criminalintent.domain.model.CommentOnServer;

import java.util.List;

import rx.Observable;

public interface ICommentsApi {
    Observable<List<CommentOnServer>> getComments(String key);
    Observable<Boolean> sendComment(CommentOnServer commentOnServer);
}
