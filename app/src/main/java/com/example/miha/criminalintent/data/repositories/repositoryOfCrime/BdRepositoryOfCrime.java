package com.example.miha.criminalintent.data.repositories.repositoryOfCrime;

import com.example.miha.criminalintent.domain.model.Crime;

import java.util.List;

import rx.Observable;

public class BdRepositoryOfCrime implements IRepositoryOfCrime {

    @Override
    public Observable<List<Crime>> getCrimes() {
        return null;
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
        return null;
    }
}
