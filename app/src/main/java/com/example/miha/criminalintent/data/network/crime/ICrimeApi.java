package com.example.miha.criminalintent.data.network.crime;

import com.example.miha.criminalintent.domain.model.Crime;

import java.util.List;

import rx.Observable;

public interface ICrimeApi {
    Observable<Crime> shareCrime(Crime crime);
    Observable<List<Crime>> getAllCrimes();

}
