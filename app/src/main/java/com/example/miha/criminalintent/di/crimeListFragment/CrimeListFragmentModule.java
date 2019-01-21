package com.example.miha.criminalintent.di.crimeListFragment;

import com.example.miha.criminalintent.data.network.CrimeApi;
import com.example.miha.criminalintent.data.repositories.repositoryOfCrime.IRepositoryOfCrime;
import com.example.miha.criminalintent.domain.crimeListFragment.CrimeListFragmentInteractor;
import com.example.miha.criminalintent.domain.crimeListFragment.ICrimeListFragmentInteractor;
import com.example.miha.criminalintent.presentation.mvp.crimeListFragment.CrimeListFragmentPresenter;
import com.example.miha.criminalintent.presentation.ui.utils.ISchedulersProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class CrimeListFragmentModule {

    @Provides
    ICrimeListFragmentInteractor provideCrimeListFragmentInteractor(ISchedulersProvider schedulersProvider, CrimeApi crimeApi, IRepositoryOfCrime repositoryOfCrime) {
        return new CrimeListFragmentInteractor(schedulersProvider, crimeApi, repositoryOfCrime);
    }
    @Provides
    CrimeListFragmentPresenter provideFragmentPresenter(ICrimeListFragmentInteractor interactor){
        return new CrimeListFragmentPresenter(interactor);
    }
}
