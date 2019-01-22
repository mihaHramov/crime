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

    public void init(){
      interactor.loadCrime(crime -> getViewState().showCrime(crime));
    }
}
