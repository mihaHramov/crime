package com.example.miha.criminalintent.presentation.mvp.CrimeListActivity;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.miha.criminalintent.domain.model.Crime;

@InjectViewState
public class CrimeListActivityPresenter extends MvpPresenter<CrimeListActivityView> {
    private Boolean isViewHasTwoPanelInterface;

    public CrimeListActivityPresenter(Boolean isTwoPanelInterface) {
        this.isViewHasTwoPanelInterface = isTwoPanelInterface;
    }
    public void choiseItemCrime(Crime crime){
        if(this.isViewHasTwoPanelInterface){
            getViewState().showCrimeInFragment(crime.getId());
        }else{
            getViewState().showCrimeInNewActivity(crime.getId());
        }
    }
}
