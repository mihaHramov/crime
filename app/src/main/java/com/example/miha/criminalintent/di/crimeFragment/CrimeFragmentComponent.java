package com.example.miha.criminalintent.di.crimeFragment;

import com.example.miha.criminalintent.presentation.mvp.crimeFragment.CrimeFragmentPresenter;

import dagger.Subcomponent;

@Subcomponent(modules = CrimeFragmentModule.class)
public interface CrimeFragmentComponent {
    CrimeFragmentPresenter getPresenter();
}
