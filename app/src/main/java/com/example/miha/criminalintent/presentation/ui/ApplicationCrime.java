package com.example.miha.criminalintent.presentation.ui;

import com.example.miha.criminalintent.di.application.ApplicationComponent;
import com.example.miha.criminalintent.di.application.ContextModule;
import com.example.miha.criminalintent.di.application.DaggerApplicationComponent;
import com.example.miha.criminalintent.di.auth.AuthComponent;
import com.example.miha.criminalintent.di.crimeFragment.CrimeFragmentComponent;
import com.example.miha.criminalintent.di.crimeFragment.CrimeFragmentModule;
import com.example.miha.criminalintent.di.crimeListActivity.CrimeListActivityComponent;
import com.example.miha.criminalintent.di.crimeListFragment.CrimeListFragmentComponnent;
import com.example.miha.criminalintent.di.crimePagerActivity.CrimePagerActivityComponent;
import com.example.miha.criminalintent.di.crimePagerActivity.CrimePagerActivityModule;
import com.example.miha.criminalintent.di.datePickerFragment.DatePickerComponent;
import com.example.miha.criminalintent.di.datePickerFragment.DatePickerModule;
import com.example.miha.criminalintent.domain.model.Crime;

public class ApplicationCrime extends android.app.Application {
    static ApplicationComponent component;

    public static AuthComponent getAuthComponent() {
        return component.getAuthComponent();
    }

    public static DatePickerComponent getDatePickerComponent(String string) {
        return component.getDatePickerComponent(new DatePickerModule(string));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public static ApplicationComponent getApplicationComponent() {
        return component;
    }

    public static CrimeListFragmentComponnent getCrimeListFragmentComponent() {
        return component.getCrimeListFragmentComponnent();
    }

    public static CrimeListActivityComponent getCrimeListActivityComponent() {
        return component.getCrimeListActivityComponent();
    }

    public static CrimeFragmentComponent getCrimeFragmentComponent(Crime crime) {
        return component.getCrimeFragmentComponent(new CrimeFragmentModule(crime));
    }

    public static CrimePagerActivityComponent getCrimePagerActivityComponent(Crime crime) {
        return component.getPagerActivityComponent(new CrimePagerActivityModule(crime));
    }
}
