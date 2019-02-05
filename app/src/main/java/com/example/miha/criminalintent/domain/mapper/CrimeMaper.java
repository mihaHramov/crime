package com.example.miha.criminalintent.domain.mapper;

import com.example.miha.criminalintent.domain.model.Crime;
import com.example.miha.criminalintent.domain.model.CrimeOnServer;

import rx.functions.Func1;

public class CrimeMaper implements Func1<Crime,CrimeOnServer> {
    @Override
    public CrimeOnServer call(Crime crime) {
        CrimeOnServer crimeOnServer = new CrimeOnServer();
        crimeOnServer.solved = crime.getSolved();
        crimeOnServer.date = crime.getDate();
        crimeOnServer.details = crime.getDetails();
        crimeOnServer.photo = crime.getPhoto();
        crimeOnServer.title = crime.getTitle();
        return crimeOnServer;
    }
}
