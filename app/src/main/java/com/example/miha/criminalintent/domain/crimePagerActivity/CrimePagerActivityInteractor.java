package com.example.miha.criminalintent.domain.crimePagerActivity;

import android.util.Log;

import com.example.miha.criminalintent.data.repositories.repositoryOfCrime.IRepositoryOfCrime;
import com.example.miha.criminalintent.domain.global.IFileTransfer;
import com.example.miha.criminalintent.domain.model.Crime;
import com.example.miha.criminalintent.domain.model.ItemCrime;
import com.example.miha.criminalintent.presentation.ui.utils.ISchedulersProvider;

import java.util.List;


public class CrimePagerActivityInteractor implements ICrimePagerActivityInteractor {
    private final ISchedulersProvider schedulers;
    private Crime crime;
    private IRepositoryOfCrime repositoryOfCrime;
    private IFileTransfer fileTransfer;

    public CrimePagerActivityInteractor(Crime crime, IRepositoryOfCrime repositoryOfCrime, ISchedulersProvider provider,IFileTransfer transfer) {
        this.crime = crime;
        this.repositoryOfCrime = repositoryOfCrime;
        this.schedulers = provider;
        this.fileTransfer = transfer;
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
    public void sendCrime(Crime crime,OnSendComplete complete,OnSendFailure failure) {
        fileTransfer.sendFileToServer(crime.getPhoto())
                .subscribeOn(schedulers.newThread())
                .observeOn(schedulers.ui())
                .doOnNext(str-> Log.d("mihaHramov","url = "+str))
                .subscribe(complete::call, error->failure.call(error.getMessage()));
    }
}
