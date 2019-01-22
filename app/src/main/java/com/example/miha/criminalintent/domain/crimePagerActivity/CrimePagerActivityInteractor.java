package com.example.miha.criminalintent.domain.crimePagerActivity;

import android.util.Log;

import com.example.miha.criminalintent.data.repositories.repositoryOfCrime.IRepositoryOfCrime;
import com.example.miha.criminalintent.domain.model.Crime;
import com.example.miha.criminalintent.presentation.ui.utils.ISchedulersProvider;

import java.util.List;


public class CrimePagerActivityInteractor implements ICrimePagerActivityInteractor {
    private final ISchedulersProvider schedulers;
    private Crime crime;
    private IRepositoryOfCrime repositoryOfCrime;

    public CrimePagerActivityInteractor(Crime crime, IRepositoryOfCrime repositoryOfCrime, ISchedulersProvider provider) {
        this.crime = crime;
        this.repositoryOfCrime = repositoryOfCrime;
        this.schedulers = provider;
    }

    @Override
    public void getCrimes(Callback callback) {
        repositoryOfCrime.getCrimes()
                .doOnNext(crimes -> Log.d("mihaHramov pager",crimes.size()+""))
                    .subscribeOn(schedulers.newThread())
                    .observeOn(schedulers.ui())
                    .subscribe(callback::success);
    }

    @Override
    public Integer getCurrentPosition(List<Crime> crimes) {
        for(int i=0;i<crimes.size();i++){
            if(crimes.get(i).getId().equals(crime.getId())){
                return i;
            }
        }
        return 0;
    }
}
