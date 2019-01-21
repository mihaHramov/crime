package com.example.miha.criminalintent.presentation.ui;

import com.example.miha.criminalintent.di.application.ApplicationComponent;
import com.example.miha.criminalintent.di.application.ContextModule;
import com.example.miha.criminalintent.di.application.DaggerApplicationComponent;
import com.example.miha.criminalintent.di.auth.AuthComponent;
import com.example.miha.criminalintent.di.crimeListActivity.CrimeListActivityComponent;
import com.example.miha.criminalintent.di.crimeListFragment.CrimeListFragmentComponnent;
import com.example.miha.criminalintent.presentation.ui.activity.CrimeListActivity;

public class ApplicationCrime extends android.app.Application {
    static ApplicationComponent component;

    public static AuthComponent getAuthComponent() {
      return  component.getAuthComponent();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public static ApplicationComponent getApplicationComponent() {
        return component;//ApplicationComponent
    }

    public static CrimeListFragmentComponnent getCrimeListFragmentComponent() {
        return component.getCrimeListFragmentComponnent();
    }
    public static CrimeListActivityComponent getCrimeListActivityComponent(){
      return   component.getCrimeListActivityComponent();
    }
}
