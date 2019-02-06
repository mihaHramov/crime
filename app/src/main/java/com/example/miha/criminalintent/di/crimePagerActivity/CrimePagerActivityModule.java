package com.example.miha.criminalintent.di.crimePagerActivity;

import com.example.miha.criminalintent.data.network.crime.ICrimeApi;
import com.example.miha.criminalintent.data.repositories.repositoryOfCrime.IRepositoryOfCrime;
import com.example.miha.criminalintent.data.repositories.repositoryOfUser.IRepositoryOfUser;
import com.example.miha.criminalintent.domain.crimePagerActivity.CrimePagerActivityInteractor;
import com.example.miha.criminalintent.domain.crimePagerActivity.ICrimePagerActivityInteractor;
import com.example.miha.criminalintent.domain.model.Crime;
import com.example.miha.criminalintent.presentation.mvp.crimePagerActivity.CrimePagerActivityPresenter;
import com.example.miha.criminalintent.presentation.ui.utils.ISchedulersProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class CrimePagerActivityModule {
    private Crime crime;

    public CrimePagerActivityModule(Crime crime) {
        this.crime = crime;
    }

    @Provides
    public ICrimePagerActivityInteractor interactor(IRepositoryOfCrime repositoryOfCrime, ISchedulersProvider provider, ICrimeApi api,IRepositoryOfUser repositoryOfUser){
        return new CrimePagerActivityInteractor(crime,repositoryOfCrime,provider,api,repositoryOfUser);
    }

    @Provides
    public CrimePagerActivityPresenter presenter(ICrimePagerActivityInteractor interactor) {
        return new CrimePagerActivityPresenter(interactor);
    }
}
