package com.example.miha.criminalintent.presentation.mvp.crimePagerActivity;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.miha.criminalintent.domain.crimePagerActivity.ICrimePagerActivityInteractor;

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
}
