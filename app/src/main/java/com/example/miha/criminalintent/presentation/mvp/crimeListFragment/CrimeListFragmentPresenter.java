package com.example.miha.criminalintent.presentation.mvp.crimeListFragment;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.miha.criminalintent.domain.crimeListFragment.ICrimeListFragmentInteractor;
import com.example.miha.criminalintent.domain.model.Crime;

import java.util.List;

@InjectViewState
public class CrimeListFragmentPresenter extends MvpPresenter<CrimeListFragmentsView> {
    private ICrimeListFragmentInteractor interactor;

    public CrimeListFragmentPresenter(ICrimeListFragmentInteractor interactor) {
        this.interactor = interactor;
    }

    public CrimeListFragmentPresenter init(){
        interactor.getAllCrimes(new ICrimeListFragmentInteractor.Callback() {
            @Override
            public void onSuccess(List<Crime> crimes) {
                getViewState().showCrimes(crimes);
            }

            @Override
            public void onError() {

            }
        });

        return this;
    }

    public void clickOnItem(Crime crime) {
        getViewState().showDetailsCrime(crime);
    }

    public void clickDeleteCrime(Crime crime) {
      //  crimeLab.deleteCrime(crime);
        //getViewState().showCrimes(crimeLab.getCrimes());
    }
}
