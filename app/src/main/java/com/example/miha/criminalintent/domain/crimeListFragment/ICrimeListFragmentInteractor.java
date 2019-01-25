package com.example.miha.criminalintent.domain.crimeListFragment;

import com.example.miha.criminalintent.domain.model.Crime;

import java.util.List;

public interface ICrimeListFragmentInteractor {
    interface Callback {
        void onSuccess(List<Crime> crimes);
    }
    void getAllCrimes(Callback callback);
}
