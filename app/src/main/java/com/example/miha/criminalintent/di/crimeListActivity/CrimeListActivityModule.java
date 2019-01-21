package com.example.miha.criminalintent.di.crimeListActivity;

import com.example.miha.criminalintent.data.repositories.repositoryOfCrime.IRepositoryOfCrime;
import com.example.miha.criminalintent.domain.crimeListActivity.CrimeListActivityInteractor;
import com.example.miha.criminalintent.domain.crimeListActivity.ICrimeListActivityInteractor;
import com.example.miha.criminalintent.presentation.mvp.crimeListActivity.CrimeListActivityPresenter;
import com.example.miha.criminalintent.presentation.ui.utils.ISchedulersProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class CrimeListActivityModule {
    @Provides
    public ICrimeListActivityInteractor provideInteractor(IRepositoryOfCrime repositoryOfCrime, ISchedulersProvider provider) {
        return new CrimeListActivityInteractor(repositoryOfCrime,provider);
    }

    @Provides
    public CrimeListActivityPresenter providePresenter(ICrimeListActivityInteractor interactor) {
        return new CrimeListActivityPresenter(interactor);
    }
}
