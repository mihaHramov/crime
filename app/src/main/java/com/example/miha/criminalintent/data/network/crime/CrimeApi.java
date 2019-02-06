package com.example.miha.criminalintent.data.network.crime;

import com.example.miha.criminalintent.domain.global.IFileTransfer;
import com.example.miha.criminalintent.domain.mapper.CrimeMaper;
import com.example.miha.criminalintent.domain.model.Crime;
import com.example.miha.criminalintent.domain.model.CrimeOnServer;
import com.example.miha.criminalintent.presentation.ui.utils.ImageUrlConverter;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

import rx.Observable;
import rx.Single;
import rx.SingleSubscriber;
import rx.functions.Func1;

public class CrimeApi implements ICrimeApi {
    private IFileTransfer fileTransfer;
    private static final String TABLE = "crimes";
    private DatabaseReference databaseReference;

    public CrimeApi(IFileTransfer fileTransfer, DatabaseReference databaseReference) {
        this.fileTransfer = fileTransfer;
        this.databaseReference = databaseReference;
    }

    private Observable<String> sendFile(Crime crime) {
        return fileTransfer.sendFileToServer(crime.getPhoto());
    }

    private Boolean isSendFile(Crime crime) {
        return !(crime.getPhoto().isEmpty() || ImageUrlConverter.isUrl(crime.getPhoto()));
    }

    @Override
    public Observable<Crime> shareCrime(Crime crime) {
        return sendImageFromCrime(crime)
                .flatMap(this::putCrime);
    }

    private Observable<Crime> sendImageFromCrime(Crime crime) {
        return Observable.just(crime)
                .flatMap(
                        (Func1<Crime, Observable<String>>) crime12 ->
                                isSendFile(crime12) ? sendFile(crime12) : Observable.just(crime12.getPhoto()),
                        (crime1, s) -> {
                            crime1.setPhoto(s);
                            return crime1;
                        });
    }

    private Observable<Crime> putCrime(Crime crime) {
        return Single.create((Single.OnSubscribe<Crime>) singleSubscriber -> putCrime(singleSubscriber, crime)).toObservable();
    }

    private void putCrime(SingleSubscriber<? super Crime> singleSubscriber, Crime crime) {
        CrimeOnServer crimeOnServer = new CrimeMaper().call(crime);
        databaseReference
                .child(TABLE)
                .push()
                .setValue(crimeOnServer,
                        (databaseError, databaseReference) -> {
                            if (databaseError != null) {
                                singleSubscriber.onError(new Throwable(databaseError.getMessage()));
                            } else {
                                singleSubscriber.onSuccess(crime);
                            }
                        });
    }

    @Override
    public Observable<List<Crime>> getAllCrimes() {
        return Observable.error(new Throwable());
    }
}
