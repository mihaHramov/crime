package com.example.miha.criminalintent.domain.crimeListActivity;

import com.example.miha.criminalintent.data.repositories.repositoryOfCrime.IRepositoryOfCrime;
import com.example.miha.criminalintent.presentation.ui.utils.ISchedulersProvider;

public class CrimeListActivityInteractor implements ICrimeListActivityInteractor {
    private IRepositoryOfCrime repositoryOfCrime;
    private ISchedulersProvider schedulers;

    public CrimeListActivityInteractor(IRepositoryOfCrime repositoryOfCrime, ISchedulersProvider schedulers) {
        this.repositoryOfCrime = repositoryOfCrime;
        this.schedulers = schedulers;
    }

    @Override
    public void createNewCrime(Callback callback) {
        repositoryOfCrime.create()
                .subscribeOn(schedulers.newThread())
                .observeOn(schedulers.ui())
                .subscribe(callback::onSuccess);
    }
}
