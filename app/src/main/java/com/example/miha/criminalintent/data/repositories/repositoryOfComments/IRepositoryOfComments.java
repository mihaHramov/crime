package com.example.miha.criminalintent.data.repositories.repositoryOfComments;

import com.example.miha.criminalintent.domain.model.Comment;
import com.example.miha.criminalintent.domain.model.Crime;

import java.util.List;

import rx.Observable;

public interface IRepositoryOfComments {
    Observable<List<Comment>> getComments(Crime crime);
}
