package com.example.miha.criminalintent.domain.crimeListActivity;

import com.example.miha.criminalintent.data.repositories.repositoryOfCrime.IRepositoryOfCrime;
import com.example.miha.criminalintent.data.repositories.repositoryOfUser.IRepositoryOfUser;
import com.example.miha.criminalintent.presentation.ui.utils.ISchedulersProvider;

import rx.Observable;

public class CrimeListActivityInteractor implements ICrimeListActivityInteractor {
    private IRepositoryOfCrime repositoryOfCrime;
    private ISchedulersProvider schedulers;
    private IRepositoryOfUser repositoryOfUser;

    public CrimeListActivityInteractor(IRepositoryOfCrime repositoryOfCrime, ISchedulersProvider schedulers, IRepositoryOfUser repositoryOfUser) {
        this.repositoryOfCrime = repositoryOfCrime;
        this.schedulers = schedulers;
        this.repositoryOfUser = repositoryOfUser;
    }

    @Override
    public void createNewCrime(Callback callback) {
        Observable.zip(repositoryOfUser.getCurrentUser(),
                repositoryOfCrime.create(),
                (user, crime) -> {
                    crime.setAuthor(user);
                    return crime;
                })
                .subscribeOn(schedulers.newThread())
                .observeOn(schedulers.ui())
                .subscribe(callback::onSuccess);
    }
}
