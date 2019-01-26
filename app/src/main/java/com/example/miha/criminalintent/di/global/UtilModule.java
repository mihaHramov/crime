package com.example.miha.criminalintent.di.global;

import com.example.miha.criminalintent.domain.global.FirebaseFileTransfer;
import com.example.miha.criminalintent.domain.global.IFileTransfer;
import com.google.firebase.storage.StorageReference;

import dagger.Module;
import dagger.Provides;

@Module
public class UtilModule {
    @Provides
    IFileTransfer providFileTransfer(StorageReference reference) {
        return new FirebaseFileTransfer(reference);
    }
}
