package com.example.miha.criminalintent.di.global;

import android.content.Context;

import com.example.miha.criminalintent.data.repositories.repositoryOfCrime.IRepositoryOfCrime;
import com.example.miha.criminalintent.data.repositories.repositoryOfUser.IRepositoryOfUser;
import com.example.miha.criminalintent.data.repositories.repositoryOfUser.RepositoryOfUser;
import com.example.miha.criminalintent.domain.model.Crime;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;
import rx.Observable;

@Module
public class RepositoryModule {
    @Provides
    IRepositoryOfCrime provideRepositoryOfCrime() {
        return new IRepositoryOfCrime() {
            @Override
            public Observable<List<Crime>> getCrimes() {
                List<Crime> crimeList =  new ArrayList<>();
                for (int i = 0;i<30;i++) {

                    Crime crime = new Crime();
                    crime.setTitle("temp"+i);
                }
                return Observable.just(crimeList);
            }

            @Override
            public Boolean update(Crime crime) {
                return null;
            }

            @Override
            public Boolean delete(Crime crime) {
                return null;
            }

            @Override
            public Observable<Crime> create() {
                Crime crime = new Crime();
                crime.setTitle("temp");
                return Observable.just(crime);
            }
        };
    }

    @Provides
    IRepositoryOfUser provideRepositoryOfUser(Context context){
        return new RepositoryOfUser(context);
    }


}
