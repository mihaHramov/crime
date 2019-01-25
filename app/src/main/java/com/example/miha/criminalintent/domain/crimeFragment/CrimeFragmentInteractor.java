package com.example.miha.criminalintent.domain.crimeFragment;

import com.example.miha.criminalintent.data.repositories.repositoryOfCrime.IRepositoryOfCrime;
import com.example.miha.criminalintent.data.repositories.repositoryOfUser.IRepositoryOfUser;
import com.example.miha.criminalintent.domain.model.Crime;
import com.example.miha.criminalintent.domain.model.User;
import com.example.miha.criminalintent.presentation.ui.utils.ISchedulersProvider;

public class CrimeFragmentInteractor implements ICrimeFragmentInteractor {
    private Crime crime;
    private IRepositoryOfUser repositoryOfUser;
    private IRepositoryOfCrime repositoryOfCrime;
    private ISchedulersProvider schedulersProvider;

    public CrimeFragmentInteractor(Crime crime, IRepositoryOfCrime repositoryOfCrime, ISchedulersProvider schedulersProvider, IRepositoryOfUser repositoryOfUser) {
        this.crime = crime;
        this.repositoryOfUser = repositoryOfUser;
        this.repositoryOfCrime = repositoryOfCrime;
        this.schedulersProvider = schedulersProvider;
    }

    @Override
    public void loadCrime(LoadCrimeListener loadCrimeListener) {
        loadCrimeListener.success(crime);
    }

    @Override
    public void saveCrime() {
        repositoryOfCrime.update(crime)
                .subscribeOn(schedulersProvider.newThread())
                .observeOn(schedulersProvider.ui())
                .subscribe();
    }

    private Boolean isEnabledCrime(User user) {
        return crime.getAuthor().getId().equals(user.getId()) || crime.getAuthor().getId() == 0;
    }

    @Override
    public void isChangeableCrime(BoolCallback callback) {
        repositoryOfUser.getCurrentUser()
                .subscribeOn(schedulersProvider.newThread())
                .observeOn(schedulersProvider.ui())
                .subscribe(user -> callback.answer(this.isEnabledCrime(user)));
    }
}
