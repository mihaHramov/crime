package com.example.miha.criminalintent.di.global;

import com.example.miha.criminalintent.data.network.crime.CrimeApi;
import com.example.miha.criminalintent.data.network.crime.ICrimeApi;
import com.example.miha.criminalintent.data.network.user.AuthUserApi;
import com.example.miha.criminalintent.data.network.user.FirebaseAuthEmail;
import com.example.miha.criminalintent.data.network.user.UserStorage;
import com.example.miha.criminalintent.data.network.user.UserStorageApi;
import com.example.miha.criminalintent.domain.global.IFileTransfer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import dagger.Module;
import dagger.Provides;

@Module
public class ApiModule {


    @Provides
    ICrimeApi povideICrimeApi(IFileTransfer transfer,DatabaseReference databaseReference) {
        return new CrimeApi(transfer,databaseReference);
    }

    @Provides
    DatabaseReference provideDatabeseReference() {
        return FirebaseDatabase.getInstance().getReference();
    }

    @Provides
    FirebaseAuth provideFirebaseAuth() {
        return FirebaseAuth.getInstance();
    }

    @Provides
    AuthUserApi provideUserApi(FirebaseAuth auth) {
        return new FirebaseAuthEmail(auth);
    }

    @Provides
    StorageReference provideStorageReference() {
        return FirebaseStorage.getInstance().getReference();
    }

    @Provides
    UserStorageApi provideUserStorageApi(DatabaseReference dbRef) {
        return new UserStorage(dbRef);
    }
}
