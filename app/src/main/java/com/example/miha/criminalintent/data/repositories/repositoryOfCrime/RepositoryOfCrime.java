package com.example.miha.criminalintent.data.repositories.repositoryOfCrime;

import com.example.miha.criminalintent.domain.model.Crime;

import java.util.List;

import rx.Observable;

public class RepositoryOfCrime implements IRepositoryOfCrime {
    private BdRepositoryOfCrime bdRepositoryOfCrime;

    public RepositoryOfCrime(BdRepositoryOfCrime bdRepositoryOfCrime) {
        this.bdRepositoryOfCrime = bdRepositoryOfCrime;
    }

    @Override
    public Observable<List<Crime>> getCrimes() {
        return Observable.fromCallable(() -> bdRepositoryOfCrime.getCrimes());
    }

    @Override
    public Boolean update(Crime crime) {
        return null;
    }

    @Override
    public Boolean delete(Crime crime) {
        return null;
    }

    @Override
    public Observable<Crime> create() {
        return Observable.fromCallable(() -> bdRepositoryOfCrime.create());
    }
}
