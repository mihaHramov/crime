package com.example.miha.criminalintent.di.global;

import com.example.miha.criminalintent.data.network.CrimeApi;
import com.example.miha.criminalintent.data.network.user.FirebaseAuthEmail;
import com.example.miha.criminalintent.data.network.user.UserApi;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import dagger.Module;
import dagger.Provides;
import rx.Observable;

@Module
public class ApiModule {

    @Provides
    CrimeApi provideCrimeApi() {
        return () -> Observable.error(new Throwable());
    }

    @Provides
    FirebaseAuth provideFirebaseAuth() {
        return FirebaseAuth.getInstance();
    }

    @Provides
    UserApi provideUserApi(FirebaseAuth auth) {
        return new FirebaseAuthEmail(auth);
    }

    @Provides
    StorageReference provideStorageReference() {
        return FirebaseStorage.getInstance().getReference();
    }
}
