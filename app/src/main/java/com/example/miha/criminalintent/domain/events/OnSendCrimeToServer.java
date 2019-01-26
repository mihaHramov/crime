package com.example.miha.criminalintent.domain.events;

import com.example.miha.criminalintent.domain.model.Crime;

public class OnSendCrimeToServer {
    private Crime crime;

    public OnSendCrimeToServer(Crime crime) {
        this.crime = crime;
    }

    public Crime getCrime() {
        return crime;
    }
}
