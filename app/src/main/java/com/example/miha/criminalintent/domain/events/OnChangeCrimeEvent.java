package com.example.miha.criminalintent.domain.events;

import com.example.miha.criminalintent.domain.model.Crime;

public class OnChangeCrimeEvent {
    private Crime crime;

    public OnChangeCrimeEvent(Crime crime) {
        this.crime = crime;
    }

    public Crime getCrime() {
        return crime;
    }
}
