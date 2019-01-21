package com.example.miha.criminalintent.di.crimeListFragment;

import com.example.miha.criminalintent.presentation.mvp.crimeListFragment.CrimeListFragmentPresenter;

import dagger.Subcomponent;

@Subcomponent(modules = CrimeListFragmentModule.class)
public interface CrimeListFragmentComponnent {
    CrimeListFragmentPresenter getPresenter();
}
