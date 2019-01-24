package com.example.miha.criminalintent.domain.crimeFragment;

import com.example.miha.criminalintent.domain.model.Crime;

public interface ICrimeFragmentInteractor {
    void saveCrime();

    interface LoadCrimeListener {
        void success(Crime crime);
    }
    void loadCrime(LoadCrimeListener loadCrimeListener);
}
