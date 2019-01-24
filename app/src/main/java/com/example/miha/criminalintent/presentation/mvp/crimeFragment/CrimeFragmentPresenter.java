package com.example.miha.criminalintent.presentation.mvp.crimeFragment;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.miha.criminalintent.domain.crimeFragment.ICrimeFragmentInteractor;

@InjectViewState
public class CrimeFragmentPresenter extends MvpPresenter<CrimeFragmentView> {
    private ICrimeFragmentInteractor interactor;

    public CrimeFragmentPresenter(ICrimeFragmentInteractor crime) {
        this.interactor = crime;
    }

    public void init() {
        interactor.loadCrime(crime -> {
            if (!crime.getPhoto().isEmpty()) {
                getViewState().showPhoto(crime.getPhoto());
            }
            if (crime.getSuspect().getId() > 0) {
                getViewState().showSuspect(crime.getSuspect());
            }
            getViewState().showDate(crime.getDate());
            getViewState().showIsSolved(crime.getSolved());
            getViewState().showTitle(crime.getTitle());
            getViewState().showDate(crime.getDate());
        });
    }

    public void clickChangeDate() {
        interactor.loadCrime(crime -> getViewState().showDate(crime.getDate()));
    }

    public void changeData(String date) {
        interactor.loadCrime(crime -> {
            crime.setDate(date);
            getViewState().showDate(date);
        });

    }

    public void clickOnImage() {
        interactor.loadCrime(crime -> getViewState().showBigImageInDialog(crime.getPhoto()));
    }
}
