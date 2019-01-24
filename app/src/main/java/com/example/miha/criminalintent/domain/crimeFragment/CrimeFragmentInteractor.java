package com.example.miha.criminalintent.domain.crimeFragment;

import com.example.miha.criminalintent.data.repositories.repositoryOfCrime.IRepositoryOfCrime;
import com.example.miha.criminalintent.domain.model.Crime;
import com.example.miha.criminalintent.presentation.ui.utils.ISchedulersProvider;

public class CrimeFragmentInteractor implements ICrimeFragmentInteractor {
    private Crime crime;
    private IRepositoryOfCrime repositoryOfCrime;
    private ISchedulersProvider schedulersProvider;

    public CrimeFragmentInteractor(Crime crime, IRepositoryOfCrime repositoryOfCrime, ISchedulersProvider schedulersProvider) {
        this.crime = crime;
        this.repositoryOfCrime = repositoryOfCrime;
        this.schedulersProvider = schedulersProvider;
    }

    @Override
    public void loadCrime(LoadCrimeListener loadCrimeListener) {
        loadCrimeListener.success(crime);
    }

    @Override
    public void saveCrime() {
        repositoryOfCrime.update(crime)
                .subscribeOn(schedulersProvider.newThread())
                .observeOn(schedulersProvider.ui())
                .subscribe();
    }
}
