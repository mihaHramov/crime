package com.example.miha.criminalintent.domain.crimeListFragment;

import com.example.miha.criminalintent.domain.model.ItemCrime;

import java.util.List;

public interface ICrimeListFragmentInteractor {
    interface Callback {
        void onSuccess(List<ItemCrime> crimes);
    }
    void getAllCrimes(Callback callback);
}
