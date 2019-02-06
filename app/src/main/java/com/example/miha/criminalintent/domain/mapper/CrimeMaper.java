package com.example.miha.criminalintent.domain.mapper;

import com.example.miha.criminalintent.domain.model.Crime;
import com.example.miha.criminalintent.domain.model.CrimeOnServer;

import rx.functions.Func1;

public class CrimeMaper implements Func1<Crime, CrimeOnServer> {
    @Override
    public CrimeOnServer call(Crime crime) {
        CrimeOnServer crimeOnServer = new CrimeOnServer();
        crimeOnServer.solved = crime.getSolved();
        crimeOnServer.date = crime.getDate();
        crimeOnServer.details = crime.getDetails();
        crimeOnServer.photo = crime.getPhoto();
        crimeOnServer.title = crime.getTitle();
        crimeOnServer.author_name = crime.getAuthor().getName();
        crimeOnServer.author_photo = crime.getAuthor().getPhoto();
        crimeOnServer.author_id = crime.getAuthor().getServerId();
        crimeOnServer.suspect_id = crime.getSuspect().getServerId();
        crimeOnServer.suspect_photo = crime.getSuspect().getPhoto();
        crimeOnServer.suspect_name = crime.getSuspect().getName();
        return crimeOnServer;
    }
}
