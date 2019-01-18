package com.example.miha.criminalintent.presentation.mvp.CrimeListActivity;

import com.arellomobile.mvp.MvpView;

import java.util.UUID;

public interface CrimeListActivityView extends MvpView {
    void showCrimeInNewActivity(UUID uuid);
    void showCrimeInFragment(UUID uuid);
}
