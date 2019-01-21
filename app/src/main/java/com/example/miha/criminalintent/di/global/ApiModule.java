package com.example.miha.criminalintent.di.global;

import com.example.miha.criminalintent.data.network.CrimeApi;
import com.example.miha.criminalintent.data.network.user.FirebaseAuthEmail;
import com.example.miha.criminalintent.data.network.user.UserApi;
import com.example.miha.criminalintent.domain.model.Crime;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;
import rx.Observable;

@Module
public class ApiModule {

    @Provides
    public CrimeApi provideCrimeApi() {
        return () -> {
            Crime crime = new Crime();
            crime.setSolved(false);
            crime.setTitle("yes");
            //crime.setSuspect("suspect");
            List<Crime> crimeList = new ArrayList<>();
            for (int i = 0;i<20;i++){
                crimeList.add(crime);
            }
            return Observable.just(crimeList);
        };
    }

    @Provides
    public FirebaseAuth provideFirebaseAuth(){
      return   FirebaseAuth.getInstance();
    }

    @Provides
    public UserApi provideUserApi(FirebaseAuth auth){
        return new FirebaseAuthEmail(auth);
    }
}
