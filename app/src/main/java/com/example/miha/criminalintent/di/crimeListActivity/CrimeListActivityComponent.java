package com.example.miha.criminalintent.di.crimeListActivity;

import com.example.miha.criminalintent.presentation.mvp.crimeListActivity.CrimeListActivityPresenter;

import dagger.Subcomponent;

@Subcomponent(modules = CrimeListActivityModule.class)
public interface CrimeListActivityComponent {
    CrimeListActivityPresenter getPresenter();
}
