package com.example.miha.criminalintent.data.network;

import com.example.miha.criminalintent.domain.model.Crime;

import java.util.List;

import rx.Observable;

public interface CrimeApi {
    Observable<List<Crime>> getAllCrimes();
}
