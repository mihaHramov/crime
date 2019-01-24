package com.example.miha.criminalintent.di.datePickerFragment;

import com.example.miha.criminalintent.presentation.mvp.datePicketFragment.DatePickerFragmentPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class DatePickerModule {
    private String data;

    public DatePickerModule(String data) {
        this.data = data;
    }

    @Provides
    public DatePickerFragmentPresenter providePresenter(){
        return new DatePickerFragmentPresenter(data);
    }
}
