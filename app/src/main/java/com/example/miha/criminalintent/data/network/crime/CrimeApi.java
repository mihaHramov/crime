package com.example.miha.criminalintent.data.network.crime;

import com.example.miha.criminalintent.domain.global.IFileTransfer;
import com.example.miha.criminalintent.domain.model.Crime;
import com.example.miha.criminalintent.presentation.ui.utils.ImageUrlConverter;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

public class CrimeApi implements ICrimeApi {
    private IFileTransfer fileTransfer;

    public CrimeApi(IFileTransfer fileTransfer) {
        this.fileTransfer = fileTransfer;
    }

    private Observable<String> sendFile(Crime crime) {
        return fileTransfer.sendFileToServer(crime.getPhoto());
    }

    private Boolean isSendFile(Crime crime) {
        return !(crime.getPhoto().isEmpty() || ImageUrlConverter.isUrl(crime.getPhoto()));
    }

    @Override
    public Observable<Crime> shareCrime(Crime crime) {
      return   Observable.just(crime)
                .flatMap(
                        (Func1<Crime, Observable<String>>) crime12 ->
                                isSendFile(crime12) ? sendFile(crime12) : Observable.just(crime12.getPhoto()),
                        (crime1, s) -> {
                            crime1.setPhoto(s);
                            return crime1;
                        });
    }

    @Override
    public Observable<List<Crime>> getAllCrimes() {
        return Observable.error(new Throwable());
    }
}
