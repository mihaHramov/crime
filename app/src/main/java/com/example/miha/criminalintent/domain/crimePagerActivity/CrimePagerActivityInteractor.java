package com.example.miha.criminalintent.domain.crimePagerActivity;

import com.example.miha.criminalintent.data.network.crime.ICrimeApi;
import com.example.miha.criminalintent.data.repositories.repositoryOfCrime.IRepositoryOfCrime;
import com.example.miha.criminalintent.domain.global.IFileTransfer;
import com.example.miha.criminalintent.domain.model.Crime;
import com.example.miha.criminalintent.domain.model.ItemCrime;
import com.example.miha.criminalintent.presentation.ui.utils.ISchedulersProvider;
import com.example.miha.criminalintent.presentation.ui.utils.ImageUrlConverter;

import java.util.List;

import rx.Observable;

public class CrimePagerActivityInteractor implements ICrimePagerActivityInteractor {
    private final ISchedulersProvider schedulers;
    private Crime crime;
    private IRepositoryOfCrime repositoryOfCrime;
    private IFileTransfer fileTransfer;
    private ICrimeApi api;

    public CrimePagerActivityInteractor(Crime crime, IRepositoryOfCrime repositoryOfCrime, ISchedulersProvider provider, IFileTransfer transfer, ICrimeApi iCrimeApi) {
        this.crime = crime;
        this.repositoryOfCrime = repositoryOfCrime;
        this.schedulers = provider;
        this.fileTransfer = transfer;
        this.api = iCrimeApi;
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

    private Observable<String> sendFile(Crime crime) {
        return fileTransfer.sendFileToServer(crime.getPhoto());
    }

    private Boolean isSendFile(Crime crime) {
        return !(crime.getPhoto().isEmpty() || ImageUrlConverter.isUrl(crime.getPhoto()));
    }

    @Override
    public void sendCrime(Crime crime, OnSendComplete complete, OnSendFailure failure) {
        Observable<String> pathOfPhotoCrime = isSendFile(crime) ? sendFile(crime) : Observable.just(crime.getPhoto());
        pathOfPhotoCrime
                .doOnError(string -> failure.call(string.getMessage()))
                .onErrorReturn(throwable -> "")
                .map(s -> {
                    crime.setPhoto(s);
                    return crime;
                })
                .subscribeOn(schedulers.newThread())
                .observeOn(schedulers.ui());
    }
}
