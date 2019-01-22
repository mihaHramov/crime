package com.example.miha.criminalintent.domain.crimeFragment;

import com.example.miha.criminalintent.domain.model.Crime;

public class CrimeFragmentInteractor implements ICrimeFragmentInteractor {
    private Crime crime;

    @Override
    public void loadCrime(LoadCrimeListener loadCrimeListener) {
        loadCrimeListener.success(crime);
    }

    public CrimeFragmentInteractor(Crime crime) {
        this.crime = crime;
    }
}
