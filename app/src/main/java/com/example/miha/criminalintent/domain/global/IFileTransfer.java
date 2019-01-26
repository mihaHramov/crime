package com.example.miha.criminalintent.domain.global;

import rx.Observable;

public interface IFileTransfer {
    Observable<String> sendFileToServer(String uri);
}
