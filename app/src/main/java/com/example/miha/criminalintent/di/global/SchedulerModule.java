package com.example.miha.criminalintent.di.global;

import com.example.miha.criminalintent.presentation.ui.utils.ISchedulersProvider;
import com.example.miha.criminalintent.presentation.ui.utils.SchedulersProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class SchedulerModule {

    @Provides
    ISchedulersProvider providerSchedulers(){
        return new  SchedulersProvider();
    }
}
