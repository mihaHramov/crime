package com.example.miha.criminalintent.di.global;

import android.content.Context;

import com.example.miha.criminalintent.data.network.comments.ICommentsApi;
import com.example.miha.criminalintent.data.network.user.UserStorageApi;
import com.example.miha.criminalintent.data.repositories.repositoryOfComments.IRepositoryOfComments;
import com.example.miha.criminalintent.data.repositories.repositoryOfComments.RepositoryOfComments;
import com.example.miha.criminalintent.data.repositories.repositoryOfCrime.BdRepositoryOfCrime;
import com.example.miha.criminalintent.data.repositories.repositoryOfCrime.IRepositoryOfCrime;
import com.example.miha.criminalintent.data.repositories.repositoryOfCrime.RepositoryOfCrime;
import com.example.miha.criminalintent.data.repositories.repositoryOfUser.IRepositoryOfUser;
import com.example.miha.criminalintent.data.repositories.repositoryOfUser.RepositoryOfUser;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {
    @Provides
    IRepositoryOfCrime provideRepositoryOfCrime(BdRepositoryOfCrime bd) {
        return new RepositoryOfCrime(bd);
    }

    @Provides
    BdRepositoryOfCrime provide(Context context) {
        return new BdRepositoryOfCrime(context);
    }

    @Provides
    IRepositoryOfUser provideRepositoryOfUser(Context context, BdRepositoryOfCrime db, UserStorageApi api) {
        return new RepositoryOfUser(context, db, api);
    }

    @Provides
    IRepositoryOfComments provideRepositoryOfComments(ICommentsApi api) {
        return new RepositoryOfComments(api);
    }

}
