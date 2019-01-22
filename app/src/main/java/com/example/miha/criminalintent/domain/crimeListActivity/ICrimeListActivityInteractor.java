package com.example.miha.criminalintent.domain.crimeListActivity;

import com.example.miha.criminalintent.domain.model.Crime;

public interface ICrimeListActivityInteractor {
    void createNewCrime(Callback callback);
    interface Callback{
        void onSuccess(Crime crime);
    }

}
