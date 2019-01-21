package com.example.miha.criminalintent.di.application;

import com.example.miha.criminalintent.di.auth.AuthComponent;
import com.example.miha.criminalintent.di.crimeListActivity.CrimeListActivityComponent;
import com.example.miha.criminalintent.di.crimeListFragment.CrimeListFragmentComponnent;
import com.example.miha.criminalintent.di.global.ApiModule;
import com.example.miha.criminalintent.di.global.RepositoryModule;
import com.example.miha.criminalintent.di.global.SchedulerModule;

import dagger.Component;

@Component(modules = {ContextModule.class,
        SchedulerModule.class,
        ApiModule.class,
        RepositoryModule.class})
public interface ApplicationComponent {
    CrimeListFragmentComponnent getCrimeListFragmentComponnent();

    AuthComponent getAuthComponent();
    CrimeListActivityComponent getCrimeListActivityComponent();
}
