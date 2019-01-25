package com.example.miha.criminalintent.domain.crimeListFragment;

import com.example.miha.criminalintent.data.network.CrimeApi;
import com.example.miha.criminalintent.data.repositories.repositoryOfCrime.IRepositoryOfCrime;
import com.example.miha.criminalintent.presentation.ui.utils.ISchedulersProvider;

public class CrimeListFragmentInteractor implements ICrimeListFragmentInteractor {
    private ISchedulersProvider schedulersProvider;
    private CrimeApi api;
    private IRepositoryOfCrime repositoryOfCrime;

    public CrimeListFragmentInteractor(ISchedulersProvider schedulersProvider, CrimeApi api, IRepositoryOfCrime repositoryOfCrime) {
        this.schedulersProvider = schedulersProvider;
        this.api = api;
        this.repositoryOfCrime = repositoryOfCrime;
    }

    @Override
    public void getAllCrimes(Callback callback) {
        api.getAllCrimes()
                .onErrorResumeNext(throwable -> repositoryOfCrime.getCrimes())
                .subscribeOn(schedulersProvider.newThread())
                .observeOn(schedulersProvider.ui())
                .subscribe(callback::onSuccess);
    }
}
