package com.example.miha.criminalintent.data.network.crime;

import rx.Observable;

public interface ICrimeApi {
    Observable<String> shareCrime();
}
