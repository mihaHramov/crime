package com.example.miha.criminalintent.domain.crimeListFragment;

import com.example.miha.criminalintent.data.network.crime.ICrimeApi;
import com.example.miha.criminalintent.data.repositories.repositoryOfCrime.IRepositoryOfCrime;
import com.example.miha.criminalintent.presentation.ui.utils.ISchedulersProvider;

public class CrimeListFragmentInteractor implements ICrimeListFragmentInteractor {
    private ISchedulersProvider schedulersProvider;
    private ICrimeApi api;
    private IRepositoryOfCrime repositoryOfCrime;

    public CrimeListFragmentInteractor(ISchedulersProvider schedulersProvider, ICrimeApi api, IRepositoryOfCrime repositoryOfCrime) {
        this.schedulersProvider = schedulersProvider;
        this.api = api;
        this.repositoryOfCrime = repositoryOfCrime;
    }

    @Override
    public void getAllCrimes(Callback callback) {
      //  api.getAllCrimes().flatMap(new F)
        //        .onErrorResumeNext(throwable -> repositoryOfCrime.getCrimes())
          repositoryOfCrime.getCrimes()
                .subscribeOn(schedulersProvider.newThread())
                .observeOn(schedulersProvider.ui())
                .subscribe(callback::onSuccess);
    }
}
