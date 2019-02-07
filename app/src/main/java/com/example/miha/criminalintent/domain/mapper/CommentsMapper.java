package com.example.miha.criminalintent.domain.mapper;

import com.example.miha.criminalintent.domain.model.Comment;
import com.example.miha.criminalintent.domain.model.CommentOnServer;
import com.example.miha.criminalintent.domain.model.User;

import rx.functions.Func1;

public class CommentsMapper implements Func1<CommentOnServer, Comment> {
    @Override
    public Comment call(CommentOnServer commentOnServer) {
        Comment comment = new Comment();
        comment.setMessage(commentOnServer.message);
        comment.setDate(commentOnServer.date);
        User user = new User();
        user.setName(commentOnServer.authorName);
        user.setPhoto(commentOnServer.authorPhoto);
        user.setServerId(commentOnServer.authorId);
        comment.setAuthor(user);
        return comment;
    }
}
