package com.example.miha.criminalintent.domain.crimePagerActivity;

import com.example.miha.criminalintent.data.network.crime.ICrimeApi;
import com.example.miha.criminalintent.data.repositories.repositoryOfCrime.IRepositoryOfCrime;
import com.example.miha.criminalintent.data.repositories.repositoryOfUser.IRepositoryOfUser;
import com.example.miha.criminalintent.domain.model.Crime;
import com.example.miha.criminalintent.domain.model.ItemCrime;
import com.example.miha.criminalintent.domain.model.User;
import com.example.miha.criminalintent.presentation.ui.utils.ISchedulersProvider;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;


public class CrimePagerActivityInteractor implements ICrimePagerActivityInteractor {
    private final ISchedulersProvider schedulers;
    private Crime crime;
    private IRepositoryOfCrime repositoryOfCrime;
    private IRepositoryOfUser repositoryOfUser;
    private ICrimeApi api;

    public CrimePagerActivityInteractor(Crime crime, IRepositoryOfCrime repositoryOfCrime, ISchedulersProvider provider, ICrimeApi iCrimeApi, IRepositoryOfUser repositoryOfUser) {
        this.crime = crime;
        this.repositoryOfCrime = repositoryOfCrime;
        this.schedulers = provider;
        this.api = iCrimeApi;
        this.repositoryOfUser = repositoryOfUser;
    }

    @Override
    public void getCrimes(Callback callback) {
        repositoryOfCrime.getCrimes()
                .subscribeOn(schedulers.newThread())
                .observeOn(schedulers.ui())
                .subscribe(callback::success);
    }

    @Override
    public Integer getCurrentPosition(List<ItemCrime> crimes) {
        for (int i = 0; i < crimes.size(); i++) {
            if (crimes.get(i).getCrime().getId().equals(crime.getId())) {
                return i;
            }
        }
        return 0;
    }


    @Override
    public void sendCrime(Crime crime, OnSendComplete complete, OnSendFailure failure) {
        repositoryOfUser.getCurrentUser()
                .flatMap((Func1<User, Observable<User>>) user -> user.getId() > 0 ? Observable.just(user) : Observable.error(new Throwable("please auth")))
                .doOnNext(crime::setAuthor)
                .flatMap((Func1<User, Observable<Crime>>) user -> api.shareCrime(crime))
                .subscribeOn(schedulers.newThread())
                .observeOn(schedulers.ui())
                .subscribe(complete::call, failure::call);
    }
}
