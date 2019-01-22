package com.example.miha.criminalintent.di.crimePagerActivity;

import com.example.miha.criminalintent.presentation.mvp.crimePagerActivity.CrimePagerActivityPresenter;

import dagger.Subcomponent;

@Subcomponent(modules = CrimePagerActivityModule.class)
public interface CrimePagerActivityComponent {
    CrimePagerActivityPresenter getPresenter();
}
