package com.example.miha.criminalintent.presentation.mvp.crimeListFragment;

import com.arellomobile.mvp.MvpView;
import com.example.miha.criminalintent.domain.model.Crime;
import com.example.miha.criminalintent.domain.model.ItemCrime;

import java.util.List;

public interface CrimeListFragmentsView  extends MvpView {
    void showCrimes(List<ItemCrime> crimes);

    void showDetailsCrime(Crime crime);
}
