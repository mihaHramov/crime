package com.example.miha.criminalintent.data.repositories.repositoryOfCrime;

import com.example.miha.criminalintent.domain.model.Crime;

import java.util.List;

import rx.Observable;

public interface IRepositoryOfCrime {
    Observable<List<Crime>> getCrimes();
    Boolean update(Crime crime);
    Boolean delete(Crime crime);

    Observable<Crime> create();
}
