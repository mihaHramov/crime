package com.example.miha.criminalintent.data.repositories.repositoryOfCrime;

import com.example.miha.criminalintent.domain.model.Crime;

import java.util.List;

import rx.Observable;

public interface IRepositoryOfCrime {
    Observable<List<Crime>> getCrimes();
    Observable<Boolean> update(Crime crime);
    Observable<Boolean> delete(Crime crime);

    Observable<Crime> create();
}
