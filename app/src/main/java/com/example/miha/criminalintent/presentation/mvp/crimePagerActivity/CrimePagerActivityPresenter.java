package com.example.miha.criminalintent.presentation.mvp.crimePagerActivity;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.miha.criminalintent.domain.crimePagerActivity.ICrimePagerActivityInteractor;
import com.example.miha.criminalintent.domain.model.Crime;

@InjectViewState
public class CrimePagerActivityPresenter extends MvpPresenter<CrimePagerActivityView> {
    private ICrimePagerActivityInteractor interactor;

    public CrimePagerActivityPresenter(ICrimePagerActivityInteractor interactor) {
        this.interactor = interactor;
    }

    public void init() {
        interactor.getCrimes(crime -> {
            getViewState().showCrimes(crime);
            getViewState().showCurrentPosition(interactor.getCurrentPosition(crime));
        });
    }

    public void sentCrime(Crime crime) {
        interactor.sendCrime(crime,
                string -> Log.d("mihaHramov",string),
                string -> Log.d("mihaHramov", "send error " + string)
        );
    }
}
