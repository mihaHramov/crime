package com.example.miha.criminalintent.presentation.mvp.crimeListActivity;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.miha.criminalintent.domain.crimeListActivity.ICrimeListActivityInteractor;
import com.example.miha.criminalintent.domain.model.Crime;

@InjectViewState
public class CrimeListActivityPresenter extends MvpPresenter<CrimeListActivityView> {
    private Boolean isViewHasTwoPanelInterface;
    private ICrimeListActivityInteractor interactor;

    public CrimeListActivityPresenter(ICrimeListActivityInteractor interactor) {
        this.interactor = interactor;
    }

    public void init(Boolean isTwoPanelInterface){
        this.isViewHasTwoPanelInterface = isTwoPanelInterface;
    }
    public void createNewCrime(){
        interactor.createNewCrime(new ICrimeListActivityInteractor.Callback() {
            @Override
            public void onSuccess(Crime crime) {
                CrimeListActivityPresenter.this.choiceItemCrime(crime);
            }

            @Override
            public void onError(String ex) {

            }
        });
    }

    public void choiceItemCrime(Crime crime){
        if(this.isViewHasTwoPanelInterface){
            getViewState().showCrimeInFragment(crime);
        }else{
            getViewState().showCrimeInNewActivity(crime);
        }
    }

    public void showAuth() {
        getViewState().showAuth();
    }
}
