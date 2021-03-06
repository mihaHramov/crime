package com.example.miha.criminalintent.domain.crimeFragment;

import com.example.miha.criminalintent.domain.model.Crime;

public interface ICrimeFragmentInteractor {

    void isChangeableCrime(BoolCallback callback);

    interface BoolCallback{
        void answer(Boolean flag);
    }

    void saveCrime();

    interface LoadCrimeListener {
        void success(Crime crime);
    }
    void loadCrime(LoadCrimeListener loadCrimeListener);
}
