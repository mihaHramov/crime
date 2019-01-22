package com.example.miha.criminalintent.di.crimeFragment;

import com.example.miha.criminalintent.domain.crimeFragment.CrimeFragmentInteractor;
import com.example.miha.criminalintent.domain.crimeFragment.ICrimeFragmentInteractor;
import com.example.miha.criminalintent.domain.model.Crime;
import com.example.miha.criminalintent.presentation.mvp.crimeFragment.CrimeFragmentPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class CrimeFragmentModule {
    private Crime crime;

    public CrimeFragmentModule(Crime crime) {
        this.crime = crime;
    }

    @Provides
    ICrimeFragmentInteractor interactor() {
        return new CrimeFragmentInteractor(crime);
    }

    @Provides
    CrimeFragmentPresenter presenter(ICrimeFragmentInteractor interactor) {
        return new CrimeFragmentPresenter(interactor);
    }

}
